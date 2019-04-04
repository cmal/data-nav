(ns data-nav.server
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(println "Hello from the Node!")

(defn run-server
  []
  nil)
