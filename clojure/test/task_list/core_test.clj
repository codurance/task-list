(ns task-list.core-test
  (:require
   [clojure.test :refer [deftest is]]
   [task-list.core :as sut]))

(deftest application-test
  (binding [sut/*id-seq* (atom (range))]
    (let [output (with-out-str
                   (-> (sut/create-task-list)
                       (sut/execute "show")
                       (sut/execute "add project secrets")
                       (sut/execute "add task secrets Eat more donuts.")
                       (sut/execute "add task secrets Destroy all humans.")
                       (sut/execute "show")



                       (sut/execute "add project training")
                       (sut/execute "add task training Four Elements of Simple Design")
                       (sut/execute "add task training SOLID")
                       (sut/execute "add task training Coupling and Cohesion")
                       (sut/execute "add task training Primitive Obsession")
                       (sut/execute "add task training Outside-In TDD")
                       (sut/execute "add task training Interaction-Driven Design")

                       (sut/execute "check 1")
                       (sut/execute "check 3")
                       (sut/execute "check 5")
                       (sut/execute "check 6")
                       (sut/execute "show")))]
      (is (= "secrets
  [ ] 1: Eat more donuts.
  [ ] 2: Destroy all humans.

secrets
  [x] 1: Eat more donuts.
  [ ] 2: Destroy all humans.

training
  [x] 3: Four Elements of Simple Design
  [ ] 4: SOLID
  [x] 5: Coupling and Cohesion
  [x] 6: Primitive Obsession
  [ ] 7: Outside-In TDD
  [ ] 8: Interaction-Driven Design

"
             output)))))
