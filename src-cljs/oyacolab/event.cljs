(ns oyacolab.event
  (:require [re-frame.core :refer [reg-event-fx reg-event-db]]))

(reg-event-fx
 :init
 (fn [_ _]
   ;; === Schema Definition ==================================
   ;; {:form {} :error {} :route {} :data {}}
   ;; ========================================================
   {}))

(reg-event-db
 :init-login-db
 (fn [db _]
   (-> db
       (assoc :form {})
       (assoc :error {}))))

(reg-event-db
 :route
 (fn [db [_ route]]
   (assoc db :route route)))

(reg-event-db
 :login.form.username
 (fn [db [_ username]]
   (assoc-in db [:form :username] username)))

(reg-event-db
 :login.form.password
 (fn [db [_ password]]
   (assoc-in db [:form :password] password)))

(reg-event-db
 :error
 (fn [db [_ error]]
   (assoc db :error error)))

(reg-event-db
 :admin.articles
 (fn [db [_ articles]]
   (assoc db :data articles)))
