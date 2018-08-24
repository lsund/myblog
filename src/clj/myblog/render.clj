(ns myblog.render
  "Namespace for rendering hiccup"
  (:require
   [myblog.db :as db]
   [taoensso.timbre :as logging]
   [hiccup.form :refer [form-to]]
   [hiccup.page :refer [html5 include-css include-js]]
   [myblog.util :as util]
   [myblog.html :as html]))


(defn navbar []
  [:div.mui-appbar
   [:table {:width "100%"}
    [:tr {:style "vertical-align:middle;"}
     [:td.mui--appbar-height {:width "50%"}
      (form-to {:class "navbar-button"}
               [:get "/"]
               [:input.mui-btn {:type :submit :value "Index"}])]
     [:td.mui--appbar-height {:width "50%"}
      (form-to {:class "navbar-button"}
               [:get "/blog"]
               [:input.mui-btn {:type :submit :value "Blog"}])]]]])


(defmulti content
  (fn [params] (:page params)))


(defmethod content :index [params]
  [:div.content
   [:p {:style "font-weight: bold"} "Hi! I'm Ludvig Sundström"]
   [:p "I'm a software engineer in Berlin, Germany"]
   [:p "You can contact me at"
    [:ul
     [:li [:a {:href "https://github.com/lsund"} "Github"]]
     [:li [:a {:href "https://github.com/lsund"} "Twitter"]]
     [:li [:a {:href "https://github.com/lsund"} "LinkedIn"]]]]])


(defmethod content :blog [params]
  [:body.mui-container
   [:p "Blog"]])


(defn layout [params]
  (html5
   (navbar)
   [:head
    [:title (:title params)]]
   [:body.mui-container
    (content params)
    (apply include-js (:javascripts (:config params)))
    (apply include-css (:styles (:config params)))]))


(def not-found (html5 "not found"))
