(ns mill.util.markdown
  (:require [clojure.java.io :refer [input-stream]]
            [markdown.core :refer [md-to-html]]
            [clojure.algo.generic.functor :refer [fmap]]
            [slingshot.slingshot :refer [throw+]])
  (:import [java.io StringWriter StringReader]))

(defn parse [filename]
  (try
    (let [output (StringWriter.)
          input (StringReader. (slurp filename))
          metadata (md-to-html input output :parse-meta? true)
          html (str output)]
      {:metadata (fmap first metadata) :html html})
    (catch Exception e
        (throw+ [:type :markdown-parse-error]))))
