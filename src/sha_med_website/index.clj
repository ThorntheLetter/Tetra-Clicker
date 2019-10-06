(ns sha-med-website.index
  (:require [compojure.core :refer :all]
            [compojure.route :as route])
  (:use [hiccup.page :only (html5 include-css include-js)]))


(defn index
  [modules]
  (html5
    [:head
     [:title "Sha亞Med"]
     [:link {:rel "shortcut icon" :type "image/png" :href "https://i.imgur.com/vIqx6Cp.png"}]
     [:meta {:name "viewport"
             :content "width=device-width, height=device-height, initial-scale=1,0"}]
     (include-css "home.css")]
     [:body {:ondragstart "return false" :ondrop "return false"}
      [:img#logo {:alt "" :draggable "false" :onmousedown "return false" :src "https://i.imgur.com/yaTs28n.png"}]
      [:div#flex-container
       [:div.flex-item
        [:a {:href "https://discord.gg/demas"}
         [:img.icon {:onmousedown "return false" :src "https://i.imgur.com/Gf5wSOv.png"}]]]
       [:div.flex-item
        [:a {:href "https://steamcommunity.com/groups/shamedic"}
         [:img.icon {:onmousedown "return false" :src "https://i.imgur.com/YDvq4ZA.png"}]]]
       [:div.flex-item
        [:a {:href "https://www.youtube.com/channel/UCJ6wxCCfimNDUiK2n_a3P0w"}
         [:img.icon {:onmousedown "return false" :src "https://i.imgur.com/xfoO067.png"}]]]
       [:div.flex-item
        [:a {:href "https://sha-med-drawpiles.tumblr.com/"}
         [:img.icon {:onmousedown "return false" :src "https://i.imgur.com/XLBkakE.png"}]]]
       [:div.flex-item
        [:a {:href "https://www.patreon.com/ShaMedic"}
         [:img.icon {:onmousedown "return false" :src "https://i.imgur.com/z6mzVci.png"}]]]
       [:div.flex-item
        [:a {:href "https://shop.spreadshirt.com/shamedic"}
         [:img.icon {:onmousedown "return false" :src "https://i.imgur.com/60jrH03.png"}]]]
       [:div.flex-item
        [:a {:href "clicker"}
         [:img.icon {:onmousedown "return false" :src "paperdem.png"}]]]
      ]]))

(defn init [] )
