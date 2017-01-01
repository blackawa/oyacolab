(ns oyacolab.resource.customer.articles
  (:require [liberator.core :refer [defresource]]
            [oyacolab.repository.article :as article]))

(defn- handle-ok [ctx db]
  (article/find-all-published {} {:connection db}))

(defresource articles [db]
  :allowed-methods [:get]
  :available-media-types ["application/edn"]
  :handle-ok (fn [ctx] (handle-ok ctx db)))
