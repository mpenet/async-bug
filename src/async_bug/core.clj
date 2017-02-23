(ns async-bug.core
  (:require [clojure.core.async :as a])
  (:import (java.util Date)))

(set! *warn-on-reflection* true)

(defn foo [^Date x]
  (let [y nil]
    (a/go
      (loop []
        (let [t (.getTime x)]
          (println t))))))
