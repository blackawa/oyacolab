(ns oyacolab.component.admin.login
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch subscribe]]
            [oyacolab.endpoint.authentication :refer [authenticate!]]
            [oyacolab.endpoint.auth-token :refer [check-auth-token]]))

(defn login []
  (reagent/create-class
   {:component-will-mount
    (fn []
      (check-auth-token)
      (dispatch [:init-login-db]))
    :reagent-render
    (fn []
      (let [form (subscribe [:login.form])
            error (subscribe [:error])]
        [:div
         [:h3 "login"]
         [:p.error (:error @error)]
         [:form
          [:p [:label "username" [:input {:type "text"
                                    :placeholder "username"
                                    :value (:username @form)
                                    :on-change #(dispatch [:login.form.username (-> % .-target .-value)])}]]]
          [:p [:label "password" [:input {:type "password"
                                    :placeholder "password"
                                    :value (:password @form)
                                    :on-change #(dispatch [:login.form.password (-> % .-target .-value)])}]]]
          [:button {:type "submit" :on-click #(do (.preventDefault %) (authenticate! @form))} "Login"]]]))}))
