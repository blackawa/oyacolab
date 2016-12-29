(ns oyacolab.resource.authentication
  (:require [liberator.core :refer [defresource]]
            [liberator.representation :refer [ring-response]]))

(defn- post! [ctx]
  (let [body (-> ctx (get-in [:request :body]) slurp)
        {:keys [id pw]} (-> body read-string)]
    {::token "abcdefg"}))

(defresource authentication []
  :allowed-methods [:post]
  :available-media-types ["application/edn"]
  :post! (fn [ctx] (post! ctx))
  :handle-created (fn [{token ::token}]
                    (ring-response {:body (str {:token token})})))
