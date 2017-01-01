(ns oyacolab.endpoint.article
  (:require [accountant.core :as accountant]
            [cljs.reader :refer [read-string]]
            [re-frame.core :refer [dispatch]]
            [oyacolab.endpoint.api :refer [request]]
            [oyacolab.util.cookie :refer [get-cookie]]
            [oyacolab.util.url :as url]))

(defn fetch-all []
  (let [auth-token (get-cookie :token)]
    (request (str (.. js/location -procotol) "//" (.. js/location -host) "/api/admin/articles")
             :get
             (fn [xhrio]
               (dispatch [:admin.articles (read-string (.getResponseText xhrio))]))
             :error-handler
             (fn [e xhrio]
               (when (= "401" (.getStatus xhrio))
                 (accountant/navigate! "/admin/login")))
             :headers {"Content-Type" "application/edn"
                       "Authorization" (str "Bearer " auth-token)})))

(defn save [form]
  (let [auth-token (get-cookie :token)]
    (request (str (.. js/location -procotol) "//" (.. js/location -host) "/api/admin/articles")
             :post
             (fn [xhrio]
               ;; TODO: get location from response header
               (let [location (.getResponseHeader xhrio "location")]
                 (accountant/navigate! (url/parse-uri location))))
             :error-handler
             (fn [e xhrio]
               (when (= "401" (.getStatus xhrio))
                 (accountant/navigate! "/admin/login")))
             :body (str form)
             :headers {"Content-Type" "application/edn"
                       "Authorization" (str "Bearer " auth-token)})))
