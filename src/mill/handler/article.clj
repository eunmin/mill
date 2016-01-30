(ns mill.handler.article
  (:require [ring.util.response :refer [response]]
            [selmer.parser :refer [render-file]]
            [mill.repository.blog :as blog]
            [mill.util.string :refer [parse-number]]
            [mill.config :refer [config]]
            [mill.repository.article :as article]))

(def ^:private limit (:article-count-per-page config))

(defn index [{{:keys [page]} :query-params}]
  (let [articles (article/find-all)
        total-count (count articles)
        page (or (parse-number page) 1)
        start (* (dec page) limit)
        end (if (> (+ start limit) total-count)
              total-count
              (+ start limit))]
    (response (render-file "article/index.html"
                {:blog blog/blog
                 :background-image "img/home-bg.jpg"
                 :prev-page (dec page)
                 :next-page (if (= end total-count) 0 (inc page))
                 :articles (subvec articles start end)}))))

(defn show [{{:keys [article-id]} :path-params}]
  (response (render-file "article/post.html"
              {:blog blog/blog
               :background-image "img/post-bg.jpg"
               :article (article/find article-id)})))
