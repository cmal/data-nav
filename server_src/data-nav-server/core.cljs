(ns data-nav-server.core
  (:require
   [cljs.nodejs :as nodejs]
   [figwheel.client :as fw]
   ))

(nodejs/enable-util-print!)

(defonce express (nodejs/require "express"))
(defonce serve-static (nodejs/require "serve-static"))
(defonce http (nodejs/require "http"))

(def app (express))

(. app
   (get "/hello"
        (fn [req res] (. res (send "Hello world")))))

(defn -main [& args]
  (fn []
    (doto (.createServer http #(app %1 %2))
      (.listen 3000))))

(set! *main-cli-fn* -main)

(fw/start {})
