(ns myblog.core
  "Namespace that defines the system of components."
 (:require
   [com.stuartsierra.component :as c]
   [myblog.app :as app]
   [myblog.server :as server]
   [myblog.db :as db]))

(defn new-system
  [config]
  (c/system-map :server (c/using (server/new-server (:server config))
                                 [:app])
                :app (c/using (app/new-app (:app config))
                              [:db])
                :db (c/using (db/new-db (:db config))
                             [])))
