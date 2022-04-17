(defn rng
  "computes the range of a relation"

  [R]

  (set (for [r R] (second r)))


   ;; uses "set", "for", "second"
  )

(defn inverse
  "computes the inverse (aka converse) of a relation"
  [R]

  (set (for [r R] [(second r) (first r)]))
  ;; uses "set", "for"
  )

(defn dom
  "computes the domain of a relation"

  [R]

  (set (for [r R] (first r))))

(defn image-of
  "computes the image of the element x under R"

  [R x]

  (set (for [r R :when (= (first r) x)] (second r)))

    ;; uses "set", "for" with :when, "first", "second"
  )

(defn isfunction?
  "determines whether a relation R is a function with domain A"

  [R A]

  (every? #(= (count (image-of R %)) 1) A))

(defn injective?
  "determines whether f is injective"

  [f]

  (let [g (inverse f)]
    (every?
     #(= 1 (count (image-of g %)))
     (dom g))))

(defn surjective?
  "determines whether f is surjective on codomain B"

  [f B]

  (and (rng f) B) 

  ;; uses "rng"
  )

(surjective? #{[1 1] [2 2] [3 3]} #{1 2 3})