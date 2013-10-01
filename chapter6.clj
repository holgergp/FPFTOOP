;; Excercise 1

(def factorial
  (fn [n]
    (if(or (= 1 n) (= 0 n) )
      1
      (* n
                    (factorial
                     (dec n))))))
(factorial 5)

;; Excercise 2
(def factorial-1
  (fn [something so-far]
    (if (or (= 1 something) (= 0 something))
      so-far
      (factorial-1 (dec something)
                 (* something so-far)))))

(def factorial
  (fn [n] (factorial-1 n 1)
    ))

(factorial 4)

;; Exercise 3

(def list-add
  (fn [something so-far]
    (if (empty? something)
      so-far
      (list-add (rest something)

                                (+ (first something) so-far)))))

(list-add [1 2 3 4] 0)

;; Exercise 4

(def list-mult
  (fn [something so-far]
    (if (empty? something)
      so-far
      (list-mult (rest something)

                                (* (first something) so-far)))))
(list-mult [1 2 3 4] 1)

(def list-oper
  (fn [oper something so-far]
    (if (empty? something)
      so-far
      (list-oper oper (rest something)

                                (oper (first something) so-far)))))

(list-oper * [1 2 3 4] 1)
(list-oper + [1 2 3 4] 1)

;; Exercise 5

(def combiner (fn[item mymap]
                (assoc mymap item 0)))
(def combiner2 (fn[item mymap]
                (assoc mymap item (count mymap))))
(list-oper combiner2 [:a :b :c] {} )

