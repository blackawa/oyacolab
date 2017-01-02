(ns oyacolab.repository.file-storage
  (:require [amazonica.core :refer :all]
            [amazonica.aws.s3 :as s3]
            [environ.core :refer [env]]))

(defn upload-file [k file]
  (with-credential [(:access-key env)
                    (:secret-key env)]
    (s3/put-object
     :bucket-name "oyacolab"
     :key k
     :file file))
  {:id k :file-name (.getName file)})
