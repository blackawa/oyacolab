(ns oyacolab.endpoint.site
  (:require [compojure.core :refer :all]
            [hiccup.page :refer [html5 include-js]]))

(defn- index []
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "親子で開発日記 | oya-co-lab"]]
   [:body
    [:div {:id "app"}
     [:h2 "Loading..."]]
    (include-js "/js/main.js")
    [:script "oyacolab.main.init();"]]))

(defn endpoint [{{db :spec} :db}]
  (routes
   (GET "/" [] (index))
   (GET "/articles/:id" [] (index))
   (GET "/admin/login" [] (index))
   (GET "/admin/articles" [] (index))
   (GET "/admin/articles/:id" [] (index))))
