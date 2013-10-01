;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.

(+ 3 4)
(+ 3 5)
1
*file*
(+ 1 2 3)
(odd? 2)
(number? 1)
(number? +)
(fn? +)
(fn? 1)
((fn [n] (+ n n) )4)
(def twice (fn [n] (+ n n)))
(twice 10)
(def second (fn[list] (nth list 1)))
(second '(2 3 4))
(nth '(1 2 3) 0)
(def liste '(2 3 4 5 6))
(nth liste 2)

(def third1 (fn[list] (nth list 2)))
(third1 liste)
(def third2 (fn[list] (first (rest (rest list)))))
(third2 liste)
(sequential? [1 2 2])
(def add-squares
  (fn [& numbers]
    (apply +(map * numbers numbers))))
(add-squares 1 2 5)

(def bizarre-fac(fn[number]
                  (apply * (range 1 (
                                     inc number)))))
(bizarre-fac 5)

;;(doc take)
;;(doc distinct)
;;(doc concat)
;;(doc repeat)
;;(doc interleave)
;;(doc drop-last)
;;(doc flatten)
;;(doc partition)
;;(doc every?)
;;(doc remove)
;;(doc take)

(def prefix-of? (fn[candidate sequence]
                  (= candidate
                     (take (count candidate)
                     sequence))))
(prefix-of? [1 2] [1 2 3 4])
     (prefix-of? '(2 3)[1 2 3 4])
     (prefix-of? '(1 2) [1 2 3 4])
(def tails
     (fn [seq]
       (map drop
            (range (inc (count seq)))
            (repeat (inc (count seq)) seq))))

(tails '(1 2 3 4))
(range (inc (count '(1 2 3 4))))

;;( doc map)
;;(doc repeat)
(def puzzle (fn [list] (list list) ))
(puzzle '(1 2 3))