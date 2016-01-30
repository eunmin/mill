(ns user
  (:require [clojure.java.io :refer [resource]]
            [io.pedestal.http :as server]
            [io.pedestal.service-tools.dev :refer [watch]]
            [mill.routes :refer [routes]]
            [mill.service :refer [service]]
            [prone-pedestal.interceptor.exceptions :refer [exceptions]]
            [selmer.parser :as selmer]))

(defn exception-interceptor [service]
  (update-in service [::server/interceptors] #(vec (cons (exceptions) %))))

(defn run-dev []
  (selmer/cache-off!)
  (selmer/set-resource-path! (resource "templates"))
  (watch)
  (-> service
    (merge {:env :dev
            ::server/join? false
            ::server/routes #(deref #'routes)
            ::server/allowed-origins {:creds true :allowed-origins (constantly true)}})
    server/default-interceptors
    (exception-interceptor)
    server/dev-interceptors
    server/create-server
    server/start))
