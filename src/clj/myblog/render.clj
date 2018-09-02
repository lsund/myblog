(ns myblog.render
  "Namespace for rendering hiccup"
  (:require
   [clojure.string :as string]
   [markdown.core :refer [md-to-html-string]]
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
               [:input.mui-btn.mui-btn--primary {:type :submit :value "Index"}])]
     [:td.mui--appbar-height {:width "50%"}
      (form-to {:class "navbar-button"}
               [:get "/blog"]
               [:input.mui-btn.mui-btn--primary {:type :submit :value "Blog"}])]]]])


(defmulti content
  (fn [params] (:page params)))


(defmethod content :index [params]
  [:div.content
   [:h3  "Hi! I'm Ludvig Sundström."]
   [:p "I'm a software engineer in Berlin, Germany, intersted in functional programming."]
   [:p "You can contact me at:"
    [:ul
     [:li [:a {:href "https://github.com/lsund"} "Github"]]
     [:li [:a {:href "https://github.com/lsund"} "Twitter"]]
     [:li [:a {:href "https://github.com/lsund"} "LinkedIn"]]]]
   [:p "And this is my " [:a {:href "/pdf/ludvig_sundstrom_resume.pdf"} "Resume."]]])


(defn- file-token->html
  [token]
  (let [md (slurp (str "resources/public/md/" token ".md"))
              html (md-to-html-string md :reference-links? true)]
          html))


(def blogposts
  (->>
   (file-seq  (clojure.java.io/file "resources/public/md"))
   (map #(.getName %))
   (filter #(.endsWith % ".md"))))


(defn filename->label
  [filename]
  (let [date (apply str (take 10 filename))]
    (str date " " (as-> (drop-last 3 (drop 10 filename)) x
                    (apply str x)
                    (string/split x #"-")
                    (map string/capitalize x)
                    (string/join " " x)))))


(defmethod content :blog [params]
  [:body.mui-container
   [:h3 "Blog Posts"]
   [:ul
    (for [post (reverse (sort blogposts))]
      [:li
       [:div.mui-panel
        [:a {:href (str "blog/" (apply str (drop-last 3 post)))}
         (filename->label post)]]])]])


(defmethod content :blogpost [{:keys [token]}]
  [:body.mui-container
   (file-token->html token)])


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
