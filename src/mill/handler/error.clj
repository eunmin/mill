(ns mill.handler.error
  (:require [ring.util.response :refer [response]]
            [selmer.parser :refer [render-file]]
            [mill.repository.blog :as blog]))

(defn not-found [request]
  (response (render-file "error/not-found.html" {:blog blog/blog
                                                 :background-image "img/error-bg.jpg"})))
