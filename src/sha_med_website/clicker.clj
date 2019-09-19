(ns sha-med-website.clicker
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(def clicks (atom 0))

(defn click-get
  [& args]
  (str @clicks))

(defn click-post
  [& args]
  (str (swap! clicks inc)))

(def routes_
  (list
    (GET "/click" [] (click-get))
    (POST "/click" [] (click-post))))
