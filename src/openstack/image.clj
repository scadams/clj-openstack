(ns openstack.image
  (:require [clj-http.client :as http]
            [openstack.const :as const]
            [openstack.identity :as identity]))


; TODO paging?
(defn get-images [identity-response]
  (http/get
    (str "host" const/images-path)
    {:accept :json
     :headers {const/x-auth-token (identity/get-token identity-response)}}))

(defn get-image-details [identity-response image-id]
  (http/get
    (str "host" (format const/image-details-path image-id))
    {:accept :json
     :headers {const/x-auth-token (identity/get-token identity-response)}}))
