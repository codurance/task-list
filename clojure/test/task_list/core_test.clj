(ns task-list.core-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [task-list.core :as sut]))

(declare execute)

(deftest application-test
  (let [task-list (sut/create-task-list)
        output (with-out-str
                 (execute "show")
                 (execute "add project secrets")
                 (execute "add task secrets Eat more donuts.")
                 (execute "add task secrets Destroy all humans.")
                 (execute "show"))]
    (is (= output
           "secrets
  [ ] 1: Eat more donuts.
  [ ] 2: Destroy all humans
"))))

(defn execute [command]
  (println command))
