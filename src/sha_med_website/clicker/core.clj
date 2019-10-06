(ns sha-med-website.clicker.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route])
  (:use [hiccup.page :only (html5 include-css include-js)]))

(def clicks2 (atom 0))

(defn index
  [req]
  (html5
    [:head
     [:title "cool clicker 2"]
     [:meta {:name "viewport"
             :content "width=device-width, height=device-height, initial-scale=1,0"}]
     (include-css "clicker/clicker.css")
     (include-js "clicker/clicker.js")]
     [:link {:rel "shortcut icon" :type "image/png" :href "https://i.imgur.com/vIqx6Cp.png"}]
     [:body {:ondragstart "return false" :ondrop "return false"}
     [:audio {:loop "" :autoplay "" :src "clicker/temvillage.ogg"}]
      [:div#content
       [:img#button.center {:alt "" :draggable "false" :onmousedown "return false" :width "200" :src "clicker/sans.png" :onclick (str "doClick(\"" (req :anti-forgery-token) "\");")}]
       [:div#bar
       [:div#currentClicks]
       [:div#baseBar
        [:div#inBar]]
       [:div#goalClicks]]
       [:div#userClicks "Your Contribution: 0"]]]))


(def clicks (atom 0))

(defn click-get
  [& args]
  (str @clicks))

(defn click-post
  [& args]
  (str (swap! clicks inc)))

(def routes_
    [(GET "/clicker" request (index request))
     (GET "/clicker/click" [] (click-get))
     (POST "/clicker/click" [] (click-post))])

(defn init [] )
