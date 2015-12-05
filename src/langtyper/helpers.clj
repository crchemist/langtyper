(ns langtyper.helpers
  (:import [java.util UUID])
  (:require [compojure.core :refer [defroutes routes wrap-routes]]
            [compojure.route :as route]
            [clojure.string :refer [join]]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.appenders.3rd-party.rotor :as rotor]
            [selmer.parser :as parser]
            [environ.core :refer [env]]
            [cheshire.core :as json]
            [clj-http.client :as http]
            [langtyper.config :refer [defaults]]
            [mount.core :as mount]))


(defn call-github
  [endpoint access-token]
  (-> (format "https://api.github.com%s%s&access_token=%s"
        endpoint
        (when-not (.contains endpoint "?") "?")
        access-token)
    http/get
    :body
    (json/parse-string (fn [^String s] (keyword (.replace s \_ \-))))))

(def get-gh-user-info (memoize (partial call-github "/user")))

(defn uuid [] (join "" (take 19 (str (UUID/randomUUID)))))
