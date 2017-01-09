(ns oyacolab.route
  (:require [secretary.core :refer-macros [defroute]]
            [re-frame.core :refer [dispatch subscribe]]
            [reagent.core :as reagent]
            [oyacolab.component.customer.index :as index]
            [oyacolab.component.customer.articles :as customer-articles]
            [oyacolab.component.not-found :as not-found]))

;; url ===> route ============================
(defroute "/" []
  (dispatch [:route [:customer :index]]))
(defroute "/articles/:id" [id]
  (dispatch [:route [:customer :article id]]))

;; route ===> view ===========================
;; inner-view ================================
(defmulti current-view #(second %))
(defmethod current-view :index []
  [index/index])
(defmethod current-view :article []
  [customer-articles/article])
(defmethod current-view :default []
  [not-found/not-found])

;; base-view =================================
(defn- customer []
  (reagent/create-class
   {:reagent-render
    (let [route (subscribe [:route])]
      (fn []
        [:div.wrap
         [:header
          [:h2 "親子開発日記 | oya-co-lab"]]
         [:section
          [current-view @route]]]))}))
(defmulti current-base-view #(first %))
(defmethod current-base-view :customer []
  [customer])
(defmethod current-base-view :default []
  [(fn [] [:div])])
