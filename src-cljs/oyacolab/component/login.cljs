(ns oyacolab.component.login
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch subscribe]]
            [oyacolab.endpoint.authentication :refer [authenticate!]]))

(defn login []
  (reagent/create-class
   {:component-will-mount
    (dispatch [:init-login-db])
    :reagent-render
    (fn []
      (let [form (subscribe [:login.form])]
        [:div
         [:h3 "login"]
         [:p (str "form: " @form)]
         [:form
          [:p [:label "id" [:input {:type "text"
                                    :placeholder "id"
                                    :value (:id @form)
                                    :on-change #(dispatch [:login.form.id (-> % .-target .-value)])}]]]
          [:p [:label "pw" [:input {:type "password"
                                    :placeholder "pw"
                                    :value (:pw @form)
                                    :on-change #(dispatch [:login.form.pw (-> % .-target .-value)])}]]]
          [:button {:type "submit" :on-click #(do (.preventDefault %) (authenticate! @form))} "Login"]]]))}))
