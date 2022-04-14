(defn image-of
  "computes the image of the element x under R"

  [R x]

  (set (for [r R :when (= (first r) x)] (second r)))

  ;; uses "set", "for" with :when, "first", "second"
)

(defn transitive?
  "tests whether R is transitive"
  [R]

  (for [r R] (subset? (image-of R (second r)) (image-of R (first r))))

		;; Transitive: relation in which if (a,b) and (b,c) exist then (a,c) also exists

    ;; hint: you might want to exploit the fact that R is transitive iff for every
    ;;       pair (a, b) in R, the image of b under R is a subset of the image 
    ;;       of a under R

   ;; uses "every?", "subset?", "image-of", "second", "first"
  )

(transitive? #{[1 1] [1 2] [1 3] [2 2] [2 3] [3 3]})
 