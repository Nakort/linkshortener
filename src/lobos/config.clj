(ns lobos.config
  (:use lobos.connectivity))

(def db
    {   :classname "org.postgresql.Driver"
        :user (System/getenv "DB_USER")
        :password (System/getenv "DB_PASSWORD")
        :subname (System/getenv "DATABASE_URL")
        :subprotocol "postgresql"})

(defn init []
    (open-global (db)))
