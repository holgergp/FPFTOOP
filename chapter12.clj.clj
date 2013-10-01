;;ex1
(def rrange
     (fn [first past-end]
       (new clojure.lang.LazySeq
            (fn []
              (if (= first past-end)
                nil
                (cons first
                      (rrange (inc first) past-end)))))))

(def rmap
  (fn [operator sequence]
  (new clojure.lang.LazySeq
            (fn []
              (if (empty? sequence)
                nil
                (cons (operator (first sequence))
                      (rmap operator (rest sequence))))))))

 (rmap inc [1 2 3 4 5])


 ;;ex2
 (def rfilter
  (fn [operator sequence]
  (new clojure.lang.LazySeq
            (fn []

               (cond (empty? sequence)

                nil
                (operator (first sequence))
                (cons (first sequence) (rfilter operator (rest sequence)))
                :else
                (rfilter operator (rest sequence))
                      )))))

(rfilter even? (range 10))
(filter even? (range 10))
 ;;ex3
 (def rfilter-eager
  (fn [operator sequence]


               (cond (empty? sequence)

                nil
                (operator (first sequence))
                (cons (first sequence) (rfilter-eager operator (rest sequence)))
                :else
                (rfilter-eager operator (rest sequence))
                      )))
 (rfilter-eager even? (range 10))

 (def eager?
     (fn [filter-function]
       (try
         (not (first (filter-function (fn [elt]
                                        (if (= elt 9999)
                                          (throw (Error.)))
                                        true)
                                      (range 10000))))
         (catch Error e
           true))))

 (eager? rfilter-eager)
 (eager? rfilter)



