(ns oyacolab.endpoint.authentication
  (:require [accountant.core :as accountant]
            [cljs.reader :refer [read-string]]
            [re-frame.core :refer [dispatch]]
            [oyacolab.endpoint.api :refer [request]]
            [oyacolab.util.cookie :refer [set-cookie!]]))

(defn authenticate! [body]
  (request (str "http://" (.. js/location -host) "/api/authentication")
           :post
           (fn [xhrio]
             (set-cookie! :token (:token (read-string (.getResponseText xhrio))))
             (accountant/navigate! "/admin/articles"))
           :error-handler
           (fn [_ xhrio]
             (dispatch [:error (read-string (.getResponseText xhrio))]))
           :body (str body)
           :headers {"Content-Type" "application/edn"}))
