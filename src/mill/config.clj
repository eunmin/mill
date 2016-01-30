(ns mill.config
  (:require [clojure.java.io :refer [resource]]
            [environ.core :refer [env]]))

(def default-config {})

(def config
  (try
    (read-string (slurp (resource (or (env :config) "config.edn"))))
    (catch Exception _
      default-config)))
