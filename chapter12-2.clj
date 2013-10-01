;;ex12-6-1
 (def prompt-and-read
     (fn []
       (print "> ")
       (.flush *out*)
       (.readLine
        (new java.io.BufferedReader *in*))))

(def inputs (repeatedly prompt-and-read))
(def one-character-line? (fn [line] (= (count line) 1)))
(def singles (filter one-character-line? inputs))

(first singles)


