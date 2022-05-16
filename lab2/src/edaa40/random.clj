(ns edaa40.lab2)
(use 'clojure.set)

(def TinyWeb #{[0 1] [0 2] [1 2] [2 1] [2 3]})

(defn dom
  "computes the domain of a relation"

  [R]

  (set (for [r R] (first r))))

(defn rng
  "computes the range of a relation"

  [R]

  (set (for [r R] (second r))))

(defn image-of
  "computes the image of the element x under R"

  [R x]

  (set
   (for [p R :when (= x (first p))]
     (second p))))

(dom TinyWeb)
(rng TinyWeb)

(defn all-pages
  "computes set of all pages on the web"

   [web]
   (union (dom web) (rng web))
;;   hint: check out the Clojure function "union"
)
(all-pages TinyWeb) 
;;(test? "all-pages" (all-pages TinyWeb) #{0 1 2 3})

(defn no-links? 
  "determines whether a page in a web has no outgoing links"
  
  [web page] 

  (if (empty? (image-of web page)) true false)
)

(no-links? TinyWeb 3)

(defn random-page 
  "picks a random page occurring in a web"

  [web] 

  (rand-nth (seq(all-pages web)))
  
;;   hint: Check out "rand-nth". Also, if you need to convert a set into a sequence, 
;;         you want to take a look at "seq".
)

(number? (random-page TinyWeb))


(defn random-link 
 
  "picks a random outgoing link from a page in a web,
  returns the page the link points to"
  
  [web page] 
  
  ;;få fram de länkarna där outgoing noden är page

  (rand-nth (seq (for [w web :when (= page (first w))] 
    w
  )))

;;   hint: If you've done the previous one, this one should be easy.
)


(random-link TinyWeb 0)

(defn surf

  "simulates one step by a surfer, 
  with boredom probability 1-alpha,
  returns the page the surfer goes to"

  [web current]
  
  (if (< (rand 1) alpha) (random-page web) (if (no-links? web current) (random-page web) (random-link web current)))

  ;;hint: check out "rand"
)


(defn random-surfer
  "simulates a random surfer for a number of steps, returns page visit stats"

  [web steps]

  (loop [current (random-page web)
         visits (into {} (map #(vector % 0) (all-pages web)))
         i steps]
    (if (zero? i)
      visits
      (recur (surf web current) (assoc visits current (inc (visits current))) (dec i))))
)

(defn page-rank
  "produces random surfing visit stats scaled down by the number of steps"

  [web steps]

  (let
   [visits (random-surfer web steps)]

    (into {} (map #(vector % (double (/ (visits %) steps))) (keys visits))))
)

(page-rank TinyWeb 100000)