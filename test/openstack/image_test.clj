(ns openstack.image-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as http]
            [openstack.image :as image]
            [openstack.identity :as identity]))


;simply ensures that the response from the GET is returned
(deftest get-images-test
  (let [mock-response {:mock "response"}]
    (with-redefs [http/get (fn [url data] mock-response)
                  identity/get-token (fn [identity-response] "token")]
      (let [response (image/get-images {})]
        (is = (response
                mock-response))))))

;simply ensures that the response from the GET is returned
(deftest get-image-details-test
  (let [mock-response {:mock "response"}]
    (with-redefs [http/get (fn [url data] mock-response)
                  identity/get-token (fn [identity-response] "token")]
      (let [response (image/get-image-details {} "image-id")]
        (is = (response
                mock-response))))))
