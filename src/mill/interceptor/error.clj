(ns mill.interceptor.error
  (:require [io.pedestal.interceptor.error :refer [error-dispatch]]
            [ring.util.response :refer [redirect]]))

(def handler-error
  (error-dispatch [context exception]
    [{:object [:type :markdown-parse-error]}]
    (assoc context :response (redirect "/404"))

    :else
    (throw exception)))


