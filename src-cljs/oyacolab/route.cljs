(ns oyacolab.route
  (:require [secretary.core :refer-macros [defroute]]
            [re-frame.core :refer [dispatch subscribe]]
            [reagent.core :as reagent]
            [oyacolab.component.index :as index]
            [oyacolab.component.article :as article]
            [oyacolab.component.not-found :as not-found]
            [oyacolab.component.login :as login]))

;; url ===> route ============================
(defroute "/" []
  (dispatch [:route [:customer :index]]))
(defroute "/article/:id" [id]
  (dispatch [:route [:customer :article id]]))
(defroute "/login" []
  (dispatch [:route [:admin :login]]))

;; route ===> view ===========================
;; inner-view ================================
(defmulti current-view #(second %))
(defmethod current-view :index []
  [index/index])
(defmethod current-view :article []
  [article/article])
(defmethod current-view :login []
  [login/login])
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
          [:h2 "親子開発日記 | oya-co-lab"]
          [:p (str "route: " @route)]]
         [:section
          [current-view @route]]]))}))
(defn- admin []
  (reagent/create-class
   {:reagent-render
    (let [route (subscribe [:route])]
      (fn []
        [:div.wrap
         [:header
          [:h2 "管理画面"]]
         [:section
          [current-view @route]]]))}))
(defmulti current-base-view #(first %))
(defmethod current-base-view :customer []
  [customer])
(defmethod current-base-view :admin []
  [admin])
(defmethod current-base-view :default []
  [customer])
