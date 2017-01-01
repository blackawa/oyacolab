(ns oyacolab.endpoint.api
  (:require [compojure.core :refer :all]
            [oyacolab.resource.authentication :refer [authentication]]
            [oyacolab.resource.auth-token :refer [auth-token]]
            [oyacolab.resource.editor :refer [editor]]
            [oyacolab.resource.admin.article :as admin-article]))

(defn endpoint [{{db :spec} :db}]
  (context "/api" _
           (ANY "/editor" _ (editor db))
           (ANY "/authentication" _ (authentication db))
           (ANY "/auth-token" _ (auth-token db))
           (ANY "/admin/articles" _ (admin-article/articles db))
           ;; (ANY "/admin/articles/:id" _ (admin-article/article db))
           ))
