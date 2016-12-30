(ns oyacolab.endpoint.api
  (:require [compojure.core :refer :all]
            [oyacolab.resource.authentication :refer [authentication]]
            [oyacolab.resource.editor :refer [editor]]))

(defn endpoint [{{db :spec} :db}]
  (context "/api" _
           (ANY "/editor" _ (editor db))
           (ANY "/authentication" _ (authentication))))
