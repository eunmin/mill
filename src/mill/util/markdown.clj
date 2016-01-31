(ns mill.util.markdown
  (:require [clojure.java.io :refer [input-stream]]
            [markdown.core :refer [md-to-html parse-metadata parse-params]]
            [clojure.algo.generic.functor :refer [fmap]]
            [slingshot.slingshot :refer [throw+]])
  (:import [java.io StringWriter StringReader]))

(defn parse [filename & params]
  (try
    (let [params (parse-params params)
          output (StringWriter.)
          input (StringReader. (slurp filename))
          metadata (if (:only-meta? params)
                     (parse-metadata input)
                     (md-to-html input output :parse-meta? true))
          html (str output)]
      {:metadata (fmap first metadata) :html html})
    (catch Exception e
        (throw+ [:type :markdown-parse-error]))))










