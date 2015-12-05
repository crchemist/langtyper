(ns langtyper.routes.races
  (:require [langtyper.layout :as layout]
            [langtyper.db.core :as db]
            [langtyper.helpers :as helpers]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [ring.util.response :refer [redirect]]
            [clojure.java.io :as io]
            [langtyper.helpers :refer [uuid]]
            [clj-http.client :as http]
            [clojure.walk :refer [keywordize-keys]]))


(defn races-get [req]
  (let [user_id (name (:identity req))
        race (helpers/get-race user_id)]
    (do
      (println (str "Users race: " (race :id)))
      (println (str "User: " user_id))
      (if (and (= (race :state) 0) (empty? (db/get-current-race {:user_id user_id})))
        (db/join-user-race! {:user_id user_id
                             :race_id (race :id)}))
      {:body {:id (race :id)
              :state (race :state)
              :track (race :track)}})))


(defroutes races-routes
  (GET "/races/get/" req (races-get req)))
