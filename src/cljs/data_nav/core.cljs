(ns data-nav.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frisk.core :refer [enable-re-frisk!]]
              [data-nav.events]
              [data-nav.subs]
              [data-nav.views :as views]
              [data-nav.config :as config]
              #_[cljs.nodejs :as nodejs]
              #_[data-nav.server :as server]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

#_(def -main
  (fn []
    (doto (.createServer https #(app %1 %2))
      (.listen 3000))))
