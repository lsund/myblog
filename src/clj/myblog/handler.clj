(ns myblog.handler
  "Namespace for handling routes"
  (:require
   [compojure.route :as r]
   [compojure.core :refer [routes GET POST ANY]]


   [ring.util.response :refer [redirect]]
   [ring.middleware
    [defaults :refer [site-defaults wrap-defaults]]
    [keyword-params :refer [wrap-keyword-params]]
    [params :refer [wrap-params]]]

   ;; Logging
   [taoensso.timbre :as logging]
   [taoensso.timbre.appenders.core :as appenders]

   [myblog.db :as db]
   [myblog.util :as util]
   [myblog.render :as render]))





(defn- app-routes
  [config]
  (routes
   (GET "/" []
        (render/layout {:title "Ludvig Sundström"
                        :page :index
                        :config config}))
   (GET "/blog" []
        (render/layout {:title "Ludvig Sundström - Blog"
                        :page :blog
                        :config config}))
   (GET "/blog/:token" [token]
        (render/layout {:title "Ludvig Sundström - Blog"
                        :page :blogpost
                        :config config
                        :token token}))
   (r/resources "/")
   (r/not-found render/not-found)))

(defn new-handler
  [config]
  (-> (app-routes config)
      (wrap-keyword-params)
      (wrap-params)
      (wrap-defaults
       (-> site-defaults (assoc-in [:security :anti-forgery] false)))))
