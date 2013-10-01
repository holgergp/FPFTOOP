;; ex 11.5.1

(require '[clojure.zip :as zip])



;; This is a handy function for inserting into a flow:
;; (-> zipper zlog zip/right zlog...)
(def zlog
     (fn [z] (println "LOG:" (pr-str (zip/node z))) z))

;; This prints the tree above the current node.
(def zuplog
     (fn [z] (zlog (zip/up z)) z))


;; For the first set of exercises
(def flattenize
     (fn [tree]
       (letfn [(flatten-zipper [so-far zipper]
                 (cond (zip/end? zipper)
                       so-far

                       (zip/branch? zipper)
                       (flatten-zipper so-far (zip/next zipper))

                       :else
                       (flatten-zipper (cons (zip/node zipper) so-far)
                                       (zip/next zipper))))]
         (reverse (flatten-zipper '() (zip/seq-zip tree))))))

(flattenize '(fn [a b](concat [a] [b])))

(def all-vectors
     (fn [tree]
       (letfn [(all-from-zipper [so-far zipper]
                 (cond (zip/end? zipper)
                       so-far

                       (zip/branch? zipper)
                       (all-from-zipper so-far (zip/next zipper))

                       (vector? (zip/node zipper))
                       (all-from-zipper (cons (zip/node zipper) so-far)
                                       (zip/next zipper))


                       :else
                       (all-from-zipper so-far (zip/next zipper))))
               ]
         (reverse (all-from-zipper '() (zip/seq-zip tree))))))


(prn (all-vectors '(fn [a b](concat [a] [b]))))

;; ex 11.5.2

(def first-vector
     (fn [tree]
       (letfn [(first-from-zipper [so-far zipper]
                 (cond (zip/end? zipper)
                       nil

                       (zip/branch? zipper)
                       (first-from-zipper so-far (zip/next zipper))

                       (vector? (zip/node zipper))
                       (zip/node zipper)


                       :else
                       (first-from-zipper so-far (zip/next zipper))))
               ]
         (first-from-zipper '() (zip/seq-zip tree)))))


(prn(first-vector '(fn [a b](concat [a] [b]))))

(def start (-> (zip/seq-zip '(/ (+ 1 HERE) 2)) zip/down))

(def nav (-> start zip/right ))
(def end (-> start zip/right zip/right zip/remove zip/remove))
(zip/node end)

;; ex 11.5.3


(def mytumult
     (fn [form]
       (letfn [(helper [zipper]
                       (cond (zip/end? zipper)
                             zipper

                             (= (zip/node zipper) '+)
                             (-> zipper
                                 (zip/replace 'PLUS)
                                 zip/next
                                 helper)

                             (branchEqualTo (zipper '-))

                             (-> zipper
                                 (zip/append-child 55555)
                                 zip/next
                                 helper)

                             (branchEqualTo (zipper '*))
                             (-> zipper
                                 (zip/replace '(/ 1 (+ 3 (- 0 9999))))
                                 zip/next
                                 helper)

                             (= (zip/node zipper) '/)
                             (-> zipper
                                 zip/right
                                 zip/remove
                                 zip/right
                                 zip/remove
                                 (zip/insert-right (-> zipper zip/right zip/node))
                                 (zip/insert-right (-> zipper zip/right zip/right zip/node))
                                  zip/next
                                 helper)

                             :else
                             (-> zipper zip/next helper)))

               (equalTo [zipper char]
                 (= (-> zipper zip/down zip/node) char))
               (branchEqualTo [zipper char]
                              (and (zip/branch? zipper) equalTo char ))
               ]
       (-> form zip/seq-zip helper zip/root))))

(def at?
     (fn [zipper subtree] (= (zip/node zipper) subtree)))

(def above?
     (fn [zipper subtree]
       (and (zip/branch? zipper)

(def tumult
     (fn [form]
       (letfn [(advancing [flow]
                          (-> (flow) zip/next do-node))
               (do-node [zipper]
                        (cond (zip/end? zipper)
                              zipper

                              (at? zipper '+)
                              (advancing (fn [] (zip/replace zipper 'PLUS)))

                              (above? zipper '-)
                              (advancing (fn [] (zip/append-child zipper 55555)))

                              (above? zipper '*)
                              (advancing (fn [] (zip/replace zipper
                                                             '(/ 1 (+ 3 (- 0 9999))))))

                              (at? zipper '/)
                              (advancing (fn []
                                           (-> zipper
                                               zip/right
                                               zip/remove
                                               zip/right
                                               zip/remove
                                               (zip/insert-right (-> zipper zip/right zip/node))
                                               (zip/insert-right (-> zipper zip/right zip/right zip/node))
                                               zip/next
                                               do-node)))

                              :else
                              (advancing (constantly zipper))))]
         (-> form zip/seq-zip do-node zip/root))))
