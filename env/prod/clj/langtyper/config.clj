(ns langtyper.config
  (:require [taoensso.timbre :as timbre]))

(def defaults
  {:init
   (fn []
     (timbre/info "\n-=[langtyper started successfully]=-"))
   :middleware identity})
