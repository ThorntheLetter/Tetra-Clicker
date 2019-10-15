(defproject sha-med_website"0.2.2-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "1.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [reagent "0.8.1"]
                 [hiccup "2.0.0-alpha2"]
                 [compojure "1.6.1"]
                 [ring/ring "1.7.1"]
                 [jumblerg/ring-cors "2.0.0"]
                 ;[ring-cors "0.1.13"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-cljsbuild "1.1.7"]]
  :ring {:handler sha-med-website.handler/app
         :init sha-med-website.handler/init}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
