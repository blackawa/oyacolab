(ns oyacolab.resource.admin.file
  (:require [clj-time.core :as time]
            [clj-time.jdbc]
            [clojure.java.io :as io]
            [liberator.core :refer [defresource]]
            [oyacolab.repository.auth-token :as token]))

(defn- malformed? [ctx]
  (let [authorization (-> ctx (get-in [:request :headers]) (get "authorization"))]
    (if-let [auth-token (second (clojure.string/split authorization #"\s"))]
      [false {::data auth-token}]
      [true {::error "invalid auth token"}])))

(defn- handle-malformed [ctx]
  (let [error (::error ctx)]
    (str {:error error})))

(defn- authorized? [ctx db]
  (let [auth-token (first (token/find-by-token {:token (::data ctx)} {:connection db}))]
    (time/before? (time/now) (:expire auth-token))))

(defn- post! [ctx db]
  (let [{:keys [filename content-type tempfile size]}
        (-> ctx (get-in [:request :multipart-params "file"]))]
    (println "filename:" filename ", content-type:" content-type ", tempfile:" tempfile ", size:" size)
    (println "saving file")
    ;; (io/copy tempfile (io/file (format "./%s" filename)))
    ))

(defresource file [db]
  :allowed-methods [:post]
  :available-media-types ["multipart/form-data"]
  :malformed? malformed?
  :handle-malformed handle-malformed
  :authorized? #(authorized? % db)
  :post! #(post! % db))
