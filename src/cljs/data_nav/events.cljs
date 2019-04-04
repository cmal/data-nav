(ns data-nav.events
    (:require [re-frame.core :as re-frame]
              [data-nav.db :as db]
              [day8.re-frame.http-fx]
              [ajax.core :as ajax :refer [GET POST]]
              [data-nav.config :as conf]
              ))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :execute
 (fn [db [_ input-val]]
   #_(re-frame/dispatch [::http-get (str conf/execution-url "?token=" conf/token "&statement=" input-val)])
   #_(re-frame/dispatch [::http-get "http://localhost:6787/p/beta/stockinfogate/test"])
   (re-frame/dispatch [::http-get "https://beta.joudou.com/stockinfogate/test"])
   #_(GET #_"http://localhost:6787/p/beta/stockinfogate/test"
        "https://beta.joudou.com/stockinfogate/test"
        {:handler #(re-frame/dispatch [:test-resp %])
         :error-handler #(re-frame/dispatch [:test-bad-resp %])})))

(re-frame/reg-event-fx
 ::http-get
 (fn [db [_ url]]
   {:http-xhrio {:method          :post
                 :uri             url
                 :timeout         5000
                 :format          (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success      [::good-post-result]
                 :on-failure      [::bad-post-result]}}))

#_(POST ;; #_"/p/www/test"
      "https://beta.joudou.com/stockinfogate/execute"
      {:content-type "application/x-www-form-urlencoded; charset=UTF-8"
       :params data
       :handler #(re-frame/dispatch [:test-resp %1])
       :error-handler #(re-frame/dispatch [:test-bad-resp %])})

#_(re-frame/reg-event-fx
   ::http-post
   (fn [db [_ url data]]
     {:http-xhrio {:method          :post
                   :uri             url
                   :params          data
                   :timeout         5000
                   :format          {:content-type "application/x-www-form-urlencoded"
                                     :write .toString}
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [::good-post-result]
                   :on-failure      [::bad-post-result]}}))

(re-frame/reg-event-db
  ::good-post-result
  (fn [db [_ result]]
    (assoc db :api-good-result result)))

(re-frame/reg-event-db
  ::bad-post-result
  (fn [db [_ result]]
    (assoc db :api-bad-result result)))

(re-frame/reg-event-db
 :test-resp
 (fn [db [_ result]]
   (assoc-in db [:test-resp] result)))

(re-frame/reg-event-db
 :test-bad-resp
 (fn [db [_ result]]
   (assoc-in db [:test-bad-resp] result)))
