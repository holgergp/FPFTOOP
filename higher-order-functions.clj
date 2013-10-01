;;; `reversed-digits` takes advantage of two new facts (that aren't
;;; used in the rest of the book):
;;;
;;; 1. Java interoperability allows you to call Java methods--both
;;;    instance methods and class methods. So you can call one of
;;;    Java's Integer constructors like this:
;;;
;;;       user=> (Integer. "321")
;;;       321
;;;
;;; 2. Strings are sequences, so you can apply sequence functions
;;;    to them. The result isn't a string, though, but rather a
;;;    sequence of Java Characters:
;;;
;;;       user=> (reverse "foo")
;;;       (\o \o \f)
;;;
;;;    You can convert a character into a string with `str`:
;;;       user=> (str \o)
;;;       "o"
;;;
;;; Thanks to Jeremy W. Sherman for a version that improved on my
;;; original.

(def reversed-digits
     (fn [string]
       (map (fn [digit-char]
              (-> digit-char str Integer.))
            (reverse string))))

(def check-sum (fn [sequence] (
                               apply + (map * (range 1 (inc (count sequence))) sequence)

                         )))

(check-sum [4 8 9 3 2])

(def isbn? (fn [string]
             (zero?(rem (check-sum (reversed-digits string)) 11)
              )))

(isbn? "0131774115")
(isbn? "0977716614")
(isbn? "1934356190")


(def check-sum (fn [sequence] (
                               apply + (map (fn [position number]( * number (if (odd? position) 1 3)))
                                            (range 1 (inc (count sequence))) sequence)

                         )))

  (def upc? (fn [string]
             (zero?(rem (check-sum (reversed-digits string)) 10)
              )))


(upc? "074182265830")
(upc? "731124100023")
(upc? "722252601404") ;; This one is incorrect.

(def number-checker (fn [sequence checkfn] (apply + (map checkfn (range 1(inc (count sequence))) sequence))))


