(ns mill.util.file
  (:import [org.apache.commons.io.comparator LastModifiedFileComparator]
           [org.apache.commons.io.filefilter SuffixFileFilter]
           [org.apache.commons.io FilenameUtils]))

(def last-modified-comparator LastModifiedFileComparator/LASTMODIFIED_REVERSE)
(def markdown-suffix-filter (SuffixFileFilter. ".md"))

(defn base-name [f]
  (FilenameUtils/getBaseName (.getName f)))

(defn ls-rt [path]
  (->>
    (file-seq (clojure.java.io/file path))
    (filter #(.isFile %))
    (filter #(.accept markdown-suffix-filter %))
    (sort #(.compare last-modified-comparator %1 %2))
    vec))
