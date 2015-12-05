(ns langtyper.routes.races
  (:require [langtyper.layout :as layout]
            [langtyper.db.core :as db]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [ring.util.response :refer [redirect]]
            [clojure.java.io :as io]
            [langtyper.helpers :refer [uuid]]
            [clj-http.client :as http]
            [clojure.walk :refer [keywordize-keys]]))

(defn races-get []
  (let [race (db/get-new-race)]
    (do
      ;; try to get race with state=NEW
      (println (uuid))
      {:body {:id "10"}})))

(defroutes races-routes
  (GET "/races/get/" [] (races-get)))
