(ns langtyper.routes.races
  (:import java.util.Date)
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

(defn get-time []
  (let [d (Date.)]
  (str (.getHours d) ":" (.getMinutes d) ":" (.getSeconds d))))

(defn races-get [req]
  (let [user_id (name (:identity req))
        race (helpers/get-race user_id)]
    (do
      (println (str "Users race: " (race :id)))
      (println (str "User: " user_id))
      (println (str "User-race: " (first (db/get-race-status {:user_id user_id
                                                  :race_id (race :id)}))))
      (println (str "Time: " (get-time)))
      (if (and (= (race :state) 0) (empty? (db/get-current-race {:user_id user_id})))
        (db/join-user-race! {:user_id user_id
                             :race_id (race :id)
                             :start_time (get-time)}))
      {:body {:id (race :id)
              :state (race :state)
              :track (race :track)}})))


(defn race-finish [passed req]
  (let [user_id (name (:identity req))
        race (helpers/get-race user_id)
        race_id (race :id)
        passed (not= 0 (Integer/parseInt passed))]
    (do
      (println (str "Finish race: " user_id race_id))
      (helpers/finish-user-race user_id race passed)
      {:body {:status 200}})))

(defroutes races-routes
  (GET "/races/get/" req (races-get req))
  (GET "/races/finish/" [passed :as req] (race-finish passed req)))
