;; (def make
;;      (fn [class & args]
;;        (let [seeded {:__class_symbol__ (:__own_symbol__ class)}
;;              constructor  (:add-instance-values (:__instance_methods__ class))]
;;          (apply constructor seeded args))))
(def method-from-message (fn[message class]
                           (message (:__instance_methods__ class))))

(def apply-message-to
  (fn [class instance message args]
    (apply (method-from-message message class) instance args)
    )
  )

(def make
     (fn [class & args]
       (let [seeded {:__class_symbol__ (:__own_symbol__ class)}]
         (apply-message-to class seeded :add-instance-values args)
         )))

;; (def send-to
;;      (fn [instance message & args]
;;        (let [class (eval (:__class_symbol__ instance))
;;              method (message (:__instance_methods__ class))]
;;          (apply method instance args))))

(def class-from-instance (fn[instance]
                           (eval (:__class_symbol__ instance))))


(def send-to
     (fn [instance message & args]
       (
         apply-message-to (class-from-instance instance) instance message args
       )
     )
)






(def Point
{
  :__own_symbol__ 'Point
  :__instance_methods__

  {
    :add-instance-values (fn [this x y]
                           (assoc this :x x :y y))
    :class (fn[this] (class-from-instance this))
    :class-name :__class_symbol__
    :shift (fn [this xinc yinc]
             (make Point (+ (:x this) xinc)
                         (+ (:y this) yinc)))
    :add (fn [this other]
           (send-to this :shift (:x other)
                                (:y other)))
   }
 })

(def a-point (make Point 1 2))
(apply-message-to Point a-point :shift [1 3])
(send-to a-point :shift 27 31)







(def point2 (make Point 2 22))

(send-to (make Point 2 22) :class-name)
(send-to point2 :class)

(def Point
{
  :__own_symbol__ 'Point
  :__instance_methods__

  {
    :add-instance-values (fn [this x y]
                           (assoc this :x x :y y))
   :origin (fn[this] (make Point 0 0))
    :class (fn[this] (class-from-instance this))
    :class-name :__class_symbol__
    :shift (fn [this xinc yinc]
             (make Point (+ (:x this) xinc)
                         (+ (:y this) yinc)))
    :add (fn [this other]
           (send-to this :shift (:x other)
                                (:y other)))
   }
 })
(send-to a-point :origin)
(send-to point2 :origin)

;; For exercise 4
(def Holder
{
  :__own_symbol__ 'Holder
  :__instance_methods__
  {
    :add-instance-values (fn [this held]
                           (assoc this :held held))
  }
})
(def method-from-message (fn[message class]
                           (or (message (:__instance_methods__ class)message ))))



(def apply-message-to
  (fn [class instance message args]
    (apply (method-from-message message class) instance args)
  )
)


(send-to (make Holder "stuff") :held)
(:not-there {:a 1, :b 2})
(or nil 3)

(send-to (make Point 1 2) :some-unknown-message)
