(ns mill.routes
  (:require [io.pedestal.http :as server]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [mill.interceptor.error :refer [handler-error]]
            [mill.handler.error :as error]
            [mill.handler.article :as article]))

(defroutes routes
  [[["/" ^:interceptors [server/html-body
                         handler-error]
     {:get article/index}
     ["/404" {:get error/not-found}]
     ["/:article-id" {:get article/show}]]]])
