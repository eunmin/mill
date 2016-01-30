(ns mill.main
  (:gen-class)
  (:require [clojure.java.io :refer [resource]]
            [io.pedestal.http :as server]
            [mill.service :refer [service]]
            [selmer.parser :as selmer]))

(defn -main [& args]
  (selmer/cache-on!)
  (selmer/set-resource-path! (resource "templates"))
  (let [server (server/create-server service)]
    (server/start server)))
