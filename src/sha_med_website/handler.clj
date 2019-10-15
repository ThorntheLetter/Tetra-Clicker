(ns sha-med-website.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.session :refer [wrap-session]]
            [jumblerg.middleware.cors :refer [wrap-cors]]
            [ring.util.response :as resp]
            [ring.middleware.x-headers :as xheaders]
            [sha-med-website.clicker :as clicker]))

(defn fresponse
  [fname]
  (resp/content-type (resp/resource-response fname {:root "public"}) "text/html")) 

(defn init
  []
  (clicker/init))

(defroutes app-routes
  (GET "/" request (clicker/index request))
  (GET "/click" [] (clicker/click-get))
  (POST "/click" request (clicker/click-post request))
  (route/not-found "not found"))

(def app
  (-> app-routes
      ;(wrap-cors :access-control-allow-headers [:get :post :put :delete])
      (wrap-defaults 
        (assoc-in
          (assoc-in
            (assoc-in site-defaults
                      [:security :frame-options] {:allow-from "https://shamedic.com"})
            [:security :anti-forgery] false)
          [:session] false))))
;[:] dissoc :same-site)))) ;gross but now needs to work in iframe
