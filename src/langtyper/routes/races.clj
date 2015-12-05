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


(defn races-get []
  (let [race (db/get-new-race)]
    (do
      ;; try to get race with state=NEW
      (if (empty? race)
        (let [race_id (db/create-new-race! {:id (uuid)
                                            :track (helpers/get-random-track)})
              race (first (db/get-new-race))]
          {:body {:id (race :id)
                  :track (race :track)}})
      {:body {:id ((first race) :id)
              :track ((first race) :track)}}))))


(defroutes races-routes
  (GET "/races/get/" [] (races-get)))
