(ns oyacolab.view
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe]]
            [oyacolab.route :refer [current-view]]))

(defn index []
  (reagent/create-class
   {:reagent-render
    (let [route (subscribe [:route])]
      (fn []
        [:div.wrap
         [:header [:h2 "親子開発日記 | oya-co-lab"]]
         [:section
          [current-view @route]]]))}))
