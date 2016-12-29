(ns oyacolab.event
  (:require [re-frame.core :refer [reg-event-fx reg-event-db]]))

(reg-event-fx
 :init-app-db
 (fn [_ _] {}))

(reg-event-db
 :route
 (fn [db [_ route]]
   (assoc db :route route)))
