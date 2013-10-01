(use 'clojure.algo.monads)
;; ex1
(let [a (concat '(a b c) '(d e f))
      b (count a)]
  (odd? b))

(-> (concat '(a b c) '(d e f))
    ((fn [step1-value] (-> (count step1-value)
    ((fn [step2-value](odd? step2-value)))))))


;; ex2
(-> (concat '(a b c) '(d e f))
    ((fn [step1-value] (-> (count step1-value)
    ((fn [step2-value](odd? step2-value)))))))
;; ex3

(-> (+ 2 3) ((fn [a] (inc a))))

;; ex10.12.1

(range (* 2 2) 101 2)
(def multiples (fn [n] (range (* n 2) 101 n)))

(multiples 2)

(range 101)
(with-monad sequence-m
  (domonad [a (range 101)
            nonprime (multiples a) ]
           nonprime))
