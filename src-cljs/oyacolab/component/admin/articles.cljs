(ns oyacolab.component.admin.articles
  (:require [goog.string :as string]
            [reagent.core :as reagent]
            [re-frame.core :refer [dispatch subscribe]]
            [oyacolab.endpoint.article :as article]
            [oyacolab.endpoint.auth-token :as auth-token]))

(defn articles []
  (reagent/create-class
   {:component-will-mount
    (fn []
      (auth-token/check)
      (article/fetch-all))
    :reagent-render
    (fn []
      (let [articles (subscribe [:admin.articles])]
        [:div
         [:h3 "articles"]
         [:a {:href "/admin/articles/new"} "create new article"]
         [:table
          [:thead
           [:tr
            [:th "id"]
            [:th "title"]
            [:th "status"]]]
          [:tbody
           (map
            (fn [a]
              [:tr
               [:td (:id a)]
               [:td [:a {:href (string/format "/admin/articles/%s" (:title a))} (:title a)]]
               [:td (:article_status_id a)]])
            @articles)]]]))}))

(defn article []
  (reagent/create-class
   {:component-will-mount
    (fn [] (auth-token/check))
    :reagent-render
    (fn []
      (let [[_ _ id] @(subscribe [:route])]
        [:div
         [:h3 (str "article" id)]
         [:a {:href "/admin/articles/2"} "go to /admin/articles/2"]]))}))
