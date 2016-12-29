(ns oyacolab.endpoint.authentication
  (:require [cljs.reader :refer [read-string]]
            [oyacolab.endpoint.api :refer [request]]))

(defn- set-cookie [k v]
  (println (str k "=" v ";path=/"))
  (set! (.-cookie js/document) (str k "=" v ";path=/")))

(defn authenticate! [body]
  (request (str "http://" (.. js/location -host) "/api/authentication")
           :post
           (fn [res]
             ;; TODO: set token as cookie
             (set-cookie "token" (:token res)))
           :body (str body)
           :headers {"Content-Type" "application/edn"}))
