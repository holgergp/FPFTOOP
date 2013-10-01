;;ex1

(map (fn [n](+ 2 n))[1 2 3])

(map (comp inc inc) [1 2 3])

(map (partial + 2) [1 2 3])

;;ex2

(def sep2 (juxt filter remove))

;; ex3
(def myfun
  (let [x 3]

    (fn [] x)))
;;x error
 (myfun)

;;ex4

(def myfun2 (fn[x] (fn [] x) 3 ))


;;ex5

(def myatom (atom 0))
(swap! myatom inc)
(deref myatom)
(swap! myatom (fn[as]33))

;;ex6

(def always (fn[value] (fn [& anything] value) ))

;;ex7

(def check-sum (fn [sequence] (
                               apply + (map * (range 1 (inc (count sequence))) sequence)

                         )))

(check-sum [4 8 9 3 2])

(def isbn? (fn [string]))