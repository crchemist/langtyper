(ns langtyper.routes.home
  (:require [langtyper.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [ring.util.response :refer [redirect]]
            [clojure.java.io :as io]
            [langtyper.helpers :refer [call-github get-gh-user-info]]
            [clj-http.client :as http]
            [clojure.walk :refer [keywordize-keys]]))

(require '[cemerick.url :refer (url query->map)])

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))


(defn github-callback [code]
  (let [gh_resp (http/post "https://github.com/login/oauth/access_token"
                           {:form-params {:client_id "0ec7d46ad5dd940274ee"
                                          :client_secret "cf0c39adc58fef8ec560c6c75f62011b16a1b5ed"
                                          :code code}})
        resp-body (gh_resp :body)
        access_token ((-> resp-body query->map keywordize-keys) :access_token)]
    (do
      (println (get-gh-user-info access_token))
      (redirect "/"))))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/callback" [code] (github-callback code))
  (GET "/about/" [] (about-page)))

