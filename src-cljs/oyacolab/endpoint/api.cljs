(ns oyacolab.endpoint.api
  (:require [cljs.reader :refer [read-string]]
            [clojure.browser.net :as net]
            [goog.events :as events])
  (:import [goog.net EventType]
           [goog.net.XhrIo ResponseType]))

(defn request [url method handler & {:keys [body headers]}]
  (let [xhrio (net/xhr-connection)]
    (events/listen xhrio EventType.SUCCESS
                   (fn [e]
                     (handler (read-string (.getResponseText xhrio)))))
    (.send xhrio
           url
           (.toLowerCase (name method))
           body
           (clj->js {"Content-Type" "application/edn;charset=utf-8"}))))
