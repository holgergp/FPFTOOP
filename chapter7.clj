(-> (Math/sqrt 25) int list)

(-> 1 (- 2))



(def ex1 (fn [vec]
           (-> vec first inc (* 3) list)
         )
  )

(ex1 [1])

((fn [n] (* 2 n)) 3)

(-> 3 ((fn [n] (* 2 n))) inc)

(+ (* (+ 1 2) 3)4)

(-> 1 (+ 2) (* 3) (+ 4))
