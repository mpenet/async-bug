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
