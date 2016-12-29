(ns oyacolab.endpoint.api
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource]]
            [liberator.representation :refer [ring-response]]
            [oyacolab.resource.authentication :refer [authentication]]))

(defresource editor []
  :allowed-methods [:post]
  :available-media-types ["application/edn"]
  :post! (fn [ctx] ctx)
  :handle-ok (fn [ctx] ctx))

(defn endpoint [{:keys [db]}]
  (context "/api" _
           (ANY "/editor" _ (editor))
           (ANY "/authentication" _ (authentication))))
