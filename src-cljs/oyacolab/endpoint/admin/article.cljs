(ns oyacolab.endpoint.admin.article
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
               (when (= 401 (.getStatus xhrio))
                 (accountant/navigate! "/admin/login")))
             :headers {"Content-Type" "application/edn"
                       "Authorization" (str "Bearer " auth-token)})))

(defn save [form]
  (let [auth-token (get-cookie :token)]
    (request (str (.. js/location -procotol) "//" (.. js/location -host) "/api/admin/articles")
             :post
             (fn [xhrio]
               (let [location (.getResponseHeader xhrio "location")]
                 (accountant/navigate! (url/parse-uri location))))
             :error-handler
             (fn [e xhrio]
               (when (= 401 (.getStatus xhrio))
                 (accountant/navigate! "/admin/login")))
             :body (str form)
             :headers {"Content-Type" "application/edn"
                       "Authorization" (str "Bearer " auth-token)})))

(defn fetch-by-id [id]
  (let [auth-token (get-cookie :token)]
    (request (str (.. js/location -procotol) "//" (.. js/location -host) "/api/admin/articles/" id)
             :get
             (fn [xhrio]
               (dispatch [:admin.article (read-string (.getResponseText xhrio))]))
             :error-handler
             (fn [e xhrio]
               (when (= 401 (.getStatus xhrio))
                 (accountant/navigate! "/admin/login")))
             :headers {"Content-Type" "application/edn"
                       "Authorization" (str "Bearer " auth-token)})))

(defn put [form]
  (let [auth-token (get-cookie :token)]
    (request (str (.. js/location -procotol) "//" (.. js/location -host) "/api/admin/articles/" (:id form))
             :put
             (fn [xhrio]
               (accountant/navigate! "/admin/articles"))
             :error-handler
             (fn [e xhrio]
               (condp = (.getStatus xhrio)
                 401 (accountant/navigate! "/admin/login")
                 409 (dispatch [:admin.articles.edit.error (.getResponseText xhrio)])
                 (js/console.err "invalid status")))
             :body (str form)
             :headers {"Content-Type" "application/edn"
                       "Authorization" (str "Bearer " auth-token)})))