(defn image-of 
  "computes the image of the element x under R"
  
  [R x] 

    (for [r R :when (= (first r) x)] (second r) )
    
    ;For every r in R when first(r) = x, return second(r). 


  ;; uses "set", "for" with :when, "first", "second"
  )

(image-of #{[1 :a] [2 :b] [1 :c] [3 :a]} 1)

