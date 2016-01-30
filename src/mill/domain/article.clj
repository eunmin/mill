(ns mill.domain.article)

(defrecord Article [id title content author date])

(defn create [article]
  (map->Article article))
