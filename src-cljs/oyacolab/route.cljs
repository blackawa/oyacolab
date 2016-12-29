(ns oyacolab.route
  (:require [secretary.core :refer-macros [defroute]]
            [re-frame.core :refer [dispatch]]
            [oyacolab.component.index :as index]
            [oyacolab.component.article :as article]
            [oyacolab.component.not-found :as not-found]))

;; url ===> route ============================
(defroute "/" []
  (dispatch [:route [:index]]))
(defroute "/:id" [id]
  (dispatch [:route [:article id]]))

;; route ===> view ===========================
(defmulti current-view #(first %))
(defmethod current-view :index []
  [index/index])
(defmethod current-view :article []
  [article/article])
(defmethod current-view :default []
  [not-found/not-found])
