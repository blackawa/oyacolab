(ns oyacolab.endpoint.authentication
  (:require [cljs.reader :refer [read-string]]
            [re-frame.core :refer [dispatch]]
            [oyacolab.endpoint.api :refer [request]]
            [secretary.core :as secretary]))

(defn- set-cookie [k v]
  (println (str k "=" v ";path=/"))
  (set! (.-cookie js/document) (str k "=" v ";path=/")))

(defn authenticate! [body]
  (request (str "http://" (.. js/location -host) "/api/authentication")
           :post
           (fn [res]
             (set-cookie "token" (:token res))
             (secretary/dispatch! "/admin/articles"))
           :error-handler
           (fn [_ xhrio]
             (dispatch [:error (read-string (.getResponseText xhrio))]))
           :body (str body)
           :headers {"Content-Type" "application/edn"}))
