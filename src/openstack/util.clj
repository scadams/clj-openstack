(ns openstack.util
  (:require [clojure.data.json :as json]
            [clojure.string :as string]
            [openstack.const :as const]))


(defn parse-json-body [response]
  (json/read-str (response :body) :key-fn keyword))

(defn create-temp-file
  ([] (create-temp-file "clj-rax-" ".tmp"))
  ([prefix suffix] (doto (java.io.File/createTempFile prefix suffix) .deleteOnExit)))

(defn get-env
  "This is a wrapper method for System/getenv."
  [value]
  (System/getenv value))

(defn build-uri
  [service & {:keys [version] :or {version 2}}]
  (let [version-str (cond
                      (and (= service "identity") (= version 2)) "v2.0"
                      :else (str "v" version))]
    (str (string/join "/" [const/get-host service version-str]) "/")))