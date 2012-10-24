(ns linkshortener.core 
  (:gen-class :main true)
  (:require [clojure.data.json :as json]
            [compojure.core :refer :all]
            [ring.middleware.json :refer :all]
            [ring.adapter.jetty :as jetty]))

(defn json-response [data & [status]]
    {:status (or status 200)
        :headers {"Content-Type" "application/json"}
        :body (json/write-str data)})

(def counter (atom 1000 ))
(def urls (atom {}))

(defn shorten
  [url]
  (let [id (swap! counter inc)
        id (Long/toString id 36)]
       (swap! urls assoc id url)
    id))

(defn redirect
  [id]
  (@urls id))

(defroutes handler 
  (POST "/"   [url] (json-response (shorten url )))
  (GET "/:id" [id]  (json-response (redirect id ))))

(def app
  (-> handler
    wrap-json-params))

(defn -main [port]
  (jetty/run-jetty #'app {:port (Integer. port)}))
