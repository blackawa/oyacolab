(ns oyacolab.component.index
  (:require [reagent.core :as reagent]))

(defn index []
  (reagent/create-class
   {:reagent-render
    (fn []
      [:div
       [:h3 "this is top page!"]
       [:ul
        [:li [:a {:href "/article/1"} "article 1"]]
        [:li [:a {:href "/article/2"} "article 2"]]
        [:li [:a {:href "/article/3"} "article 3"]]]])}))
