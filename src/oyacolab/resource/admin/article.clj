(ns oyacolab.resource.admin.article
  (:require [clj-time.core :as time]
            [clj-time.jdbc]
            [clojure.tools.logging :as log]
            [liberator.core :refer [defresource]]
            [liberator.representation :refer [ring-response]]
            [oyacolab.repository.article :as article]
            [oyacolab.repository.auth-token :as token]
            [oyacolab.repository.editor :as editor]))

(defn- malformed? [ctx]
  (let [authorization (-> ctx (get-in [:request :headers]) (get "authorization"))]
    (if-let [auth-token (second (clojure.string/split authorization #"\s"))]
      [false {::auth-token auth-token}]
      [true {::error "invalid auth token"}])))

(defn- handle-malformed [ctx]
  (let [error (::error ctx)]
    (str {:error error})))

(defn- authorized? [ctx db]
  (let [auth-token (first (token/find-by-token {:token (::auth-token ctx)} {:connection db}))]
    (time/before? (time/now) (:expire auth-token))))

(defn- handle-ok [ctx db]
  (article/find-all {} {:connection db}))

(defresource articles [db]
  :allowed-methods [:get]
  :available-media-types ["application/edn"]
  :malformed? malformed?
  :handle-malformed handle-malformed
  :authorized? (fn [ctx] (authorized? ctx db))
  :handle-ok (fn [ctx] (handle-ok ctx db)))
