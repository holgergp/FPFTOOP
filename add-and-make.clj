(def point {:x 1, :y 2, :__class_symbol__ 'Point})

(def Point
     (fn [x y]
       {:x x,
        :y y
        :__class_symbol__ 'Point}))

(def x :x)
(def y :y)
(def class-of :__class_symbol__)

(def shift
     (fn [this xinc yinc]
       (Point (+ (x this) xinc)
              (+ (y this) yinc))))

(def add1
    (fn [this that] (
                     Point
             (+ (x this)(x that))
             (+ (y this)(y that))
          )
      ))

(def add
    (fn [this that] (
         shift this (x that) (y that)
                     )

      ))


(prn (add (Point 1 2) (Point 3 4)))
(add (Point 3 2) (Point 4 5))

(def make (fn [newPointFn & coords ] (apply newPointFn coords)))
prn (make Point 2 3)
prn (Point 2 3)


(def Triangle
     (fn [point1 point2 point3]
       {:point1 point1, :point2 point2, :point3 point3
        :__class_symbol__ 'Triangle}))


(def right-triangle (Triangle (Point 0 0)
                              (Point 0 1)
                              (Point 1 0)))

(def equal-right-triangle (Triangle (Point 0 0)
                                    (Point 0 1)
                                    (Point 1 0)))

(def different-triangle (Triangle (Point 0 0)
                                  (Point 0 10)
                                  (Point 10 0)))

(def equal-triangles? (fn[first-triangle second-triangle]
                        (and (= (:point1 first-triangle)(:point1 second-triangle))
                             (= (:point2 first-triangle)(:point2 second-triangle))
                             (= (:point3 first-triangle)(:point3 second-triangle))

                             )
                   ))

(equal-triangles? right-triangle right-triangle)

(equal-triangles? right-triangle equal-right-triangle)
(equal-triangles? right-triangle different-triangle)