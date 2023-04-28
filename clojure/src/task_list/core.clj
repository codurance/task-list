(ns task-list.core
  (:require
   [clojure.string :as str]))

(defn create-task-list []
  {})

(def ^:dynamic *id-seq* (atom (range)))

(defn next-id []
  (swap! *id-seq* rest)
  (first @*id-seq*))

(defn create-task [description]
  {:id (next-id)
   :description description})


(defn add-task [task-list project description]
  (let [task (create-task description)]
    (if (get task-list project)
      (assoc task-list project (conj (get task-list project) task))
      (println (str "Could not find a project with the name " project ".")))))

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
            (do
              (println (str "Could not find a task with an ID of " id))
              task-list)))))))

(defn check [task-list id]
  (set-done task-list id true))

(defn uncheck [task-list id]
  (set-done task-list id false))

(defn error [command-line]
  (println (str "I don't know what the command " command-line "is.")))

(defn execute [task-list command-line]
  (let [[command args] (str/split command-line #" " 2)]
    (case command
      "show" (do (show task-list)
                 task-list)
      "add" (add task-list args)
      "check" (check task-list (Integer/parseInt args))
      "uncheck" (uncheck task-list (Integer/parseInt args))
      "help" (println "Commands:
  show
  add project <project name>
  add task <project name> <task description>
  check <task ID>
  uncheck <task ID>")
      "quit" (System/exit 0)
      (do (error command-line)
          task-list))))

(defn -main []
  (binding [*id-seq* (atom (range))]
   (loop [task-list (create-task-list)]
     (print "> ")
     (flush)
     (let [input (read-line)]
       (recur (execute task-list input))))))
