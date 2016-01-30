(ns mill.repository.article
  (:refer-clojure :exclude [find])
  (:require [mill.util.markdown :as markdown]
            [mill.util.file :as file]
            [environ.core :refer [env]]
            [mill.domain.article :as article]))

(def base-path (env :base-path))

(defn- id->filename [id]
  (str base-path "/" id ".md"))

(defn find [id]
  (let [article (markdown/parse (id->filename id))]
    (article/create (assoc (:metadata article)
                      :id id
                      :content (:html article)))))

(defn find-all []
  (mapv #(find (file/base-name %)) (file/ls-rt base-path)))


