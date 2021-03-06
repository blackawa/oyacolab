(ns oyacolab.endpoint.customer.article
  (:require [cljs.reader :refer [read-string]]
            [re-frame.core :refer [dispatch]]
            [oyacolab.endpoint.api :refer [request]]))

(defn fetch-all []
  (request
   (str (.. js/location -procotol) "//api.oyaco-lab.com/api/articles")
   :get
   (fn [xhrio]
     (let [res (read-string (.getResponseText xhrio))]
       (dispatch [:customer.articles res])))
   :headers {"Content-Type" "application/edn"}))

(defn fetch-by-id [id]
  (request (str (.. js/location -procotol) "//api.oyaco-lab.com/api/articles/" id)
           :get
           (fn [xhrio]
             (let [res (read-string (.getResponseText xhrio))]
               (dispatch [:customer.article res])))
           :headers {"Content-Type" "application/edn"}))
