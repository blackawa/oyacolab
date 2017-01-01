(ns oyacolab.resource.customer.article
  (:require [liberator.core :refer [defresource]]
            [oyacolab.repository.article :as article]))

(defn- malformed? [ctx]
  (let [params (-> ctx (get-in [:request :params]))]
    [false {::params params}]))

(defn- handle-ok [ctx db]
  (let [params (::params ctx)]
    (first (article/find-published-by-id {:id (read-string (:id params))} {:connection db}))))

(defresource article [db]
  :allowed-methods [:get]
  :available-media-types ["application/edn"]
  :malformed? malformed?
  :handle-ok (fn [ctx] (handle-ok ctx db)))
