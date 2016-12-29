(ns oyacolab.event
  (:require [re-frame.core :refer [reg-event-fx reg-event-db]]))

(reg-event-fx
 :init-app-db
 (fn [_ _] {}))

(reg-event-db
 :init-login-db
 (fn [db _]
   (assoc db :form {})))

(reg-event-db
 :route
 (fn [db [_ route]]
   (assoc db :route route)))

(reg-event-db
 :login.form.id
 (fn [db [_ id]]
   (assoc-in db [:form :id] id)))

(reg-event-db
 :login.form.pw
 (fn [db [_ pw]]
   (assoc-in db [:form :pw] pw)))
