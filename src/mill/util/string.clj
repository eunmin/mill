(ns mill.util.string)

(defn parse-number [^String value]
  (try
    (Long. value)
    (catch Exception e)))
