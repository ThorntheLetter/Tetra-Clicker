(ns sha-med-website.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as resp]
            [sha-med-website.clicker :as clicker]))

;(def routes_
;  (list
;    (GET "/" [] "home")
;    (route/not-found "Not Found")))

(defn fresponse
  [fname]
  (resp/content-type (resp/resource-response fname {:root "public"}) "text/html")) 

(defroutes app-routes
  (GET "/" [] (fresponse "index.html"))
  (GET "/click" [] clicker/click-get)
  (POST "/click" [] clicker/click-post)
  (route/not-found "404 Not Found"))
  

;(def app-routes 
;  (routes
;    (make-route
;      (:method 

(def app
  (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))
