(ns sha-med-website.clicker
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp])
  (:use [hiccup.page :only (html5 include-css include-js)]))

(defn index
  [req]
  (html5
    [:head
     [:title "Tetra Clicker 2"]
     [:meta {:name "viewport"
             :content "width=device-width, height=device-height, initial-scale=1,0"}]
     (include-css "clicker/clicker.css")
     (include-js "clicker/clicker.js")]
    [:link {:rel "shortcut icon" :type "image/png" :href "favicon.png"}]
    [:body {:ondragstart "return false" :ondrop "return false"}
     [:audio {:loop "" :autoplay "" :src "clicker/smwbonus.mp3"}]
     [:div#content
      [:div#ccontent
      [:img#button.center {:alt "" :draggable "false" :onmousedown "return false" :src "clicker/button.png" :onclick (str "doClick();")}]
      [:div#bar
       [:div#currentClicks]
       [:div#baseBar
        [:div#inBar]]
       [:div#goalClicks]]
      [:div#userClicks "Your Contribution: 0"]]]]))


(def clicks (atom 0))

(def ips (atom {}))

(defn click-get
  [& args]
  (str @clicks))

(defn click-post
  [req]
  (let [ip (get-in req [:headers "x-real-ip"])
        t (quot (System/currentTimeMillis) 1000)]
    (if (or
          (< (get-in @ips [ip :clicks] 0) 10)
          (not= (get-in @ips [ip :time]) t))
      (do
        (if (and
              (contains? @ips ip)
              (= (get-in @ips [ip :time] t) t))
          (swap! ips update-in [ip :clicks] inc)
          (swap! ips assoc ip {:time (quot(System/currentTimeMillis) 1000)
                               :clicks 1}))
        (str (swap! clicks inc)))
      "0")))


(defn backup []
  (spit "clicks.txt" @clicks))

(future (while true (do (Thread/sleep 1000) (backup))))

(defn init [] 
    (reset! clicks (read-string (slurp "clicks.txt"))))
