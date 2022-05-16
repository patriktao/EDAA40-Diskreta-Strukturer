(def X 'X)

(def O 'O)

(def _ '_)

(def winning-lines [[0 1 2]
                    [3 4 5]
                    [6 7 8]
                    [0 3 6]
                    [1 4 7]
                    [2 5 8]
                    [2 4 6]
                    [0 4 8]])

(defn- threeinarow
  "determines whether the specified line ln (a vector of three indices) is 
      fully occupied by player p in the board b"
  [p b ln]

  (every? true? (for [i ln] (= (nth b i) p)))
  ;;  hint: this is a very simple one; it becomes even simpler when you remember that 
  ;;        the = operator can take any number of arguments, and that lines and the board
  ;;        are vectors, and that if v is a vector and n is a number, then (v n) is the n-th
  ;;        element of the vector (starting from 0, as usual)
  )
(threeinarow X [_ _ _ _ _ _ X X X] (winning-lines 2))
;;(test? "threeinarow 1" (threeinarow X [_ _ _ _ _ _ X X X] (winning-lines 2)))
;;(test? "threeinarow 2" (not (threeinarow X [_ _ _ _ _ _ X X X]  (winning-lines 1))))
;;(test? "threeinarow 3" (not (threeinarow O [_ _ _ _ _ _ X X X]  (winning-lines 2))))

(defn win?
  "determines whether player p has fully occupied at least one of
    the lines in the variable winning-lines"

  [p b]

  (some true? (for [line winning-lines] (threeinarow p b line)))

  ;;   hint: of course, this one uses threeinarow and winning-lines. Also, "some". 
  )

(win? X [X _ _ X _ _ X _ _])
(not (win? O [X _ _ X _ _ X _ _]))

(defn opponent
  "computes the opponent of the specified player"

  [p]

  (case p
    X O
    O X
    _))

(def B0 [_ _ _
         _ _ _
         _ _ _])

;; if X gets to move next in this one, it wins:

(def B1 [_ O _
         _ X _
         _ _ _])

;; this shows why O needs to answer in the corner, if X
;; takes the center in its first move: if X gets the
;; next move in this position, it cannot do better than a draw

(def B2 [O _ _
         _ X _
         _ _ _])

(declare moves)

(defn moves
  "computes all possible moves player p can make on board b;
      it returns a list of all possible new boards after p made a move,
      or an empty list, if p cannot make any move"

    [p b]


    (map #(assoc b % p) (filter #(if(= (b %) _ ) true false)  (range 9)))
    ;; (filter #(if(= (b %) _ ) true false)  (range 9)) //Vi får fram alla index vi kan lägga något på
    ;; assoc b % p // vi lägger p på positionen % 
    ;; map: vi gör det för varje position % som går.

  ;;  hint: uses map, assoc, filter, and range.
  ;;        "assoc" is useful to "replace", in a vector such as the one representing the board,
  ;;        one element with another. (assoc b n p) returns a vector that is like b, except that 
  ;;        p is the next value at index n.
  ;;        "filter" is used to filter out those boards whose n-th field is open, i.e. has the value _.
  ;;
  ;;        All iteration happens in map and filter --- if you start writing loops, try to find a solution
  ;;        using the functions listed above.
  )

(moves X B0)
(moves X B2)
   ;;(test? "moves 1" (count (moves X B0)) 9)
   ;;(test? "moves 2" (count (moves X B1)) 7)
   ;;(test? "moves 3" (count (moves X B2)) 7)
