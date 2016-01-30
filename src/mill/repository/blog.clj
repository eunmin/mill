(ns mill.repository.blog
  (:require [mill.domain.blog :as blog]
            [mill.config :refer [config]]))

(def blog (blog/create config))
