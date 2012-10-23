(ns linkshortener.models.link
  (:require [clojure.java.jdbc :as sql]))

(defn find [code]
    (sql/with-connection (System/getenv "DATABASE_URL")
                             (sql/with-query-results results
                                                           [(str "select * from links where code=" code)]
                                                           (into [] results))))

(defn create [url code]
    (sql/with-connection (System/getenv "DATABASE_URL")
                             (sql/insert-values :links [:url :code] [url code])))
