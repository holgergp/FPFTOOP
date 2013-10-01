(use 'patterned.sweet)

(defpatterned count-sequence
  [so-far [           ] ] so-far
  [so-far [head & tail] ] (count-sequence (inc so-far) tail))
(count-sequence 0 '(:a :b :c))


(defpatterned count-sequence
  [so-far [           ] ] so-far
  [so-far [head & tail] ] (count-sequence (inc so-far) tail)
  [[head & tail] ] (count-sequence 0 tail))

(count-sequence '(:a :b :c))
