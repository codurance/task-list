(ns task-list.core
  (:require
   [clojure.walk :as walk]
   [clojure.string :as str]))

(defn create-task-list []
  {})

(def id-seq (atom (range)))

(defn next-id []
  (swap! id-seq rest)
  (first @id-seq))

(defn create-task [description]
  {:id (next-id)
   :description description})


(defn add-task [task-list project description]
  (let [task (create-task description)]
    (assoc task-list project (conj (get task-list project) task))))

(defn add-project [task-list project-name]
  (assoc task-list project-name []))

(defn add [task-list command-line]
  (let [[subcommand args] (str/split command-line #" " 2)]
    (if (= subcommand "project")
      (add-project task-list args)
      (when (= subcommand "task")
        (let [[project description] (str/split args #" " 2)]
          (add-task task-list project description))))))

(defn show [task-list]
  (doseq [[project tasks] task-list]
    (println project)
    (doseq [task tasks]
      (println (str "  [" (if (:done task) "x" " ") "] " (:id task) ": " (:description task))))
    (println "")))

(defn set-done [task-list id value]
  (let [proj-name (atom nil)
        idx (atom nil)]
    (loop [tasks (into [] task-list)]
      (let [[project project-tasks] (first tasks)]
        (doall (map-indexed (fn [i task]
                              (when (= id (:id task))
                                (reset! proj-name project)
                                (reset! idx i)))
                            project-tasks))

        (if @proj-name
          (update-in task-list [@proj-name @idx :done] (fn [_] value))
          (if (not (empty? (rest tasks)))
            (recur (rest tasks))
            task-list))))))

(defn check [task-list id]
  (set-done task-list id true))

(defn uncheck [task-list id]
  (set-done task-list id false))

(defn execute [task-list command-line]
  (let [[command args] (str/split command-line #" " 2)]
    (case command
      "show" (do (show task-list)
                 task-list)
      "add" (add task-list args)
      "check" (check task-list (Integer/parseInt args)))))

(def task-list (-> (create-task-list)
                   (add-project "secrets")
                   (add-task "secrets" "Eat more donuts.")
                   (add-task "secrets" "Destroy all humans.")
                   (check 2)
                   (uncheck 2)))
