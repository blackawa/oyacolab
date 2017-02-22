(ns oyacolab.endpoint.site
  (:require [compojure.core :refer :all]
            [hiccup.page :refer [html5 include-js]]))

(defn- index []
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:link {:rel "stylesheet" :href "/assets/normalize.css/normalize.css"}]
    [:link {:rel "stylesheet" :href "/assets/pure/pure-min.css"}]
    [:title "おやこらぼ"]]
   [:body
    [:div {:id "app"}
     [:h2 "Loading..."]]
    (include-js "/js/main.js")
    [:script "oyacolab.main.init();"]]))

(defn endpoint [{{db :spec} :db}]
  (routes
   (GET "/" [] (index))
   (GET "/articles/:id" [] (index))))
