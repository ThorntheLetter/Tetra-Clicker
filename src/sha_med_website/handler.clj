(ns sha-med-website.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as resp]
            [sha-med-website.index :as home]
            [sha-med-website.clicker.core :as clicker]))

(def modules ['clicker])

(defn dalias
  [name]
  (get (ns-aliases *ns*) name))

(def modules-ns
  (map dalias modules))

(def default-routes
  [(GET "/" [] (home/index modules))
   (route/not-found "Not Found")])

(defn fresponse
  [fname]
  (resp/content-type (resp/resource-response fname {:root "public"}) "text/html")) 

(defn modules-fn
  [f mods]
  (dorun (map #((ns-resolve % f)) mods))) ;swap to doall and combine w/ routes

(defn mod-routes
  [mods]
  (apply concat (map #(var-get (ns-resolve % 'routes_)) mods)))

(defn app-routes 
  [mods]
  (apply routes (concat (mod-routes modules-ns) default-routes)))

(defn init
  []
  (in-ns 'sha-med-website.handler)
  (modules-fn 'init modules-ns))

(def app
  (wrap-defaults (app-routes modules-ns) site-defaults))
