(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
                              config helpers)))

(defmigration add-links-table
                (up [] (create
                        (tbl :links
                         (varchar :url 100)
                         (varchar :hash 200)
                           )))
                (down [] (drop (table :links))))
