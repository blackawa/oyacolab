(ns oyacolab.main
    (:gen-class)
    (:require [com.stuartsierra.component :as component]
              [duct.util.runtime :refer [add-shutdown-hook]]
              [duct.util.system :refer [load-system]]
              [duct.component.ragtime :refer [migrate]]
              [environ.core :refer [env]]
              [clojure.java.io :as io]
              [hanami.core :as hanami]))

(defn -main [& args]
  (let [bindings {'http-port (Integer/parseInt (:port env "3000"))}
        system   (->> (load-system [(io/resource "oyacolab/system.edn")] bindings)
                      (component/start))]
    (add-shutdown-hook ::stop-system #(component/stop system))
    (println "Started HTTP server on port" (-> system :http :port))))
