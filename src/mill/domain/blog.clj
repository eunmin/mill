(ns mill.domain.blog)

(defrecord Blog [title description logo copyright])

(defn create [blog]
  (map->Blog blog))
