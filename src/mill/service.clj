(ns mill.service
  (:require [io.pedestal.http :as server]
            [environ.core :refer [env]]
            [mill.routes :refer [routes]]))

(def service {:env :prod
              ::server/routes routes
              ::server/resource-path "/public"
              ::server/type :immutant
              ::server/router :linear-search
              ::server/port (env :port 8080)})
