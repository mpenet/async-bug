(ns async-bug.core
  (:require [clojure.core.async :as a])
  (:import (java.util Date)))

(set! *warn-on-reflection* true)

;; NPE
(defn foo [x]
  (let [y nil]
    (a/go))) ;; doing stuff or not in go has no impact

;; OK
(defn foo [x]
  (let [y :foo]
    (a/go)))

;; reflection in go
(defn f []
  (let [a "foo"]
    (.substring a 1 2)
    (a/go
      (loop []
        (.substring a 1 2)
        (a/<! nil)
        (recur)))))
