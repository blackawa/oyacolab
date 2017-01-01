(ns oyacolab.component.admin.articles
  (:require [goog.string :as string]
            [reagent.core :as reagent]
            [re-frame.core :refer [dispatch subscribe]]
            [markdown.core :refer [md->html]]
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
         [:table.pure-table
          [:thead
           [:tr [:th "id"] [:th "title"] [:th "status"]]]
          [:tbody
           (map
            (fn [a]
              [:tr
               [:td (:id a)]
               [:td [:a {:href (str "/admin/articles/" (:id a))} (:title a)]]
               [:td (:article_status_id a)]])
            @articles)]]]))}))

(defn new-article []
  (reagent/create-class
   {:component-will-mount
    (fn []
      (auth-token/check)
      (dispatch [:init-new-article-db]))
    :reagent-render
    (fn []
      (let [form (subscribe [:admin.articles.new.form])
            error (subscribe [:admin.articles.new.error])]
        [:div
         [:h3 "new article"]
         [:p.error @error]
         [:form
          [:p.title
           [:label {:for "title"} "title"]
           [:input {:type "text"
                    :id "title"
                    :name "title"
                    :placeholder "title"
                    :value (:title @form)
                    :on-change #(dispatch [:admin.articles.new.title (-> % .-target .-value)])}]]
          [:p.content
           [:label {:for "content"} "content"]
           [:textarea {:id "content"
                       :name "content"
                       :placeholder "content"
                       :value (:content @form)
                       :on-change #(dispatch [:admin.articles.new.content (-> % .-target .-value)])}]]
          [:div.content-preview
           [:label "content-preview"]
           [:div {:dangerouslySetInnerHTML {:__html (md->html (:content @form))}}]]
          [:p.save-type
           [:label {:for "save-type"} "save-type"]
           [:select
            {;; use (or) to suppress error to use nil for value of <select>
             :value (or (:save-type @form) "")
             :on-change #(dispatch [:admin.articles.new.save-type (keyword (-> % .-target .-value))])}
            [:option {:value :draft} "draft"]
            [:option {:value :published} "published"]]]
          [:p [:button {:on-click #(do (.preventDefault %) (article/save @form))} "save"]]]]))}))

(defn article []
  (reagent/create-class
   {:component-will-mount
    (fn [] (auth-token/check))
    :reagent-render
    (fn []
      (let [route (subscribe [:route])
            [_ _ id] @route]
        [:div
         [:h3 (str "article" id)]]))}))