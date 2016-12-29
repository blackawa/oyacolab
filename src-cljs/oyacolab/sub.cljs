(ns oyacolab.sub
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :route
 (fn [db _]
   (:route db)))

(reg-sub
 :login.form
 (fn [db _]
   (:form db)))
