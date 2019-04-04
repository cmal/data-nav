(ns data-nav.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]))

(defn prompt-input []
  (let [val (reagent/atom "")]
    (fn [props]
      [:input (merge props
                     {:type "text"
                      :value @val
                      :auto-focus true
                      :on-change #(reset! val (-> % .-target .-value))
                      :on-key-down #(case (.-which %)
                                      13 (do
                                           (re-frame/dispatch [:execute @val])
                                           (reset! val "")) ;; enter
                                      27 (reset! val "") ;; esc
                                      nil)})])))


(defn entry []
  (let []
    (fn [json entry-data]
      [:div json])))

(defn content []
  (let [history (re-frame/subscribe [:history])]
    (fn []
      (for [entry-data history]
        ^{:key (:id entry)} [entry entry-data])
      [prompt-input])))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello from " @name]
      [content])))
