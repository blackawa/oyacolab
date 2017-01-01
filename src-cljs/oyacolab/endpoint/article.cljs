(ns oyacolab.endpoint.article
  (:require [accountant.core :as accountant]
            [cljs.reader :refer [read-string]]
            [re-frame.core :refer [dispatch]]
            [oyacolab.endpoint.api :refer [request]]
            [oyacolab.util.cookie :refer [get-cookie]]))

(defn fetch-all []
  (if-let [auth-token (get-cookie :token)]
    (request (str "http://" (.. js/location -host) "/api/admin/articles")
             :get
             (fn [res]
               (dispatch [:admin.articles res]))
             :error-handler
             (fn [e xhrio]
               (when (= "401" (.getStatus xhrio))
                 (accountant/navigate! "/admin/login")))
             :headers {"Content-Type" "application/edn"
                       "Authorization" (str "Bearer " auth-token)})))
