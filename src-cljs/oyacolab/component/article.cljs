(ns oyacolab.component.article
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe]]))

(defn article []
  (reagent/create-class
   {:reagent-render
    (fn []
      (let [route (subscribe [:route])]
        [:article
         [:h3 "article page"]
         [:div (str "route is " @route)]
         [:p [:a {:href "/"} "Go back to top page"]]]))}))
