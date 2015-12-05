(ns langtyper.routes.home
  (:require [langtyper.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [ring.util.response :refer [redirect]]
            [clojure.java.io :as io]
            [ring.util.response :refer [response]]
            [langtyper.helpers :refer [call-github get-gh-user-info]]
            [clj-http.client :as http]
            [clojure.walk :refer [keywordize-keys]]
            [buddy.auth :refer [authenticated? throw-unauthorized]]))

(require '[cemerick.url :refer (url query->map)])

(defn home-page [req]
  (if-not (authenticated? req)
    (layout/render
     "home.html" {:docs (-> "docs/docs.md" io/resource slurp)
                  :authenticated false} )
    (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)
                 :authenticated true})))

(defn about-page []
  (layout/render "about.html"))

(defn github-callback [code req]
  (let [gh_resp (http/post "https://github.com/login/oauth/access_token"
                           {:form-params {:client_id "0ec7d46ad5dd940274ee"
                                          :client_secret "cf0c39adc58fef8ec560c6c75f62011b16a1b5ed"
                                          :code code}})
        resp-body (gh_resp :body)
        access_token ((-> resp-body query->map keywordize-keys) :access_token)
        gh_user_id (str ((get-gh-user-info access_token) :id))
        session (:session req)]
    (do
      (-> (redirect "/")
          (assoc :session (assoc session :identity (keyword gh_user_id)))))))

(defn logout
  [request]
  (-> (redirect "/")
      (assoc :session {})))

(defroutes home-routes
  (GET "/" [:as req] (home-page req))
  (GET "/callback" [code :as req] (github-callback code req))
  (GET "/about/" [] (about-page))
  (GET "/logout" [req] (logout req)))
