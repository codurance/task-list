package com.codurance.training.tasks

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

class TaskList(private val `in`: BufferedReader, private val out: PrintWriter) : Runnable {

    private val tasks = LinkedHashMap<String, MutableList<Task>>()

    private var lastId: Long = 0

    override fun run() {
        while (true) {
            out.print("> ")
            out.flush()
            val command: String
            try {
                command = `in`.readLine()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

            if (command == QUIT) {
                break
            }
            execute(command)
        }
    }

    private fun execute(commandLine: String) {
        val commandRest = commandLine.split(" ".toRegex(), 2).toTypedArray()
        val command = commandRest[0]
        when (command) {
            "show" -> show()
            "add" -> add(commandRest[1])
            "check" -> check(commandRest[1])
            "uncheck" -> uncheck(commandRest[1])
            "help" -> help()
            else -> error(command)
        }
    }

    private fun show() {
        for ((key, value) in tasks) {
            out.println(key)
            for (task in value) {
                out.printf("    [%c] %d: %s%n", if (task.isDone) 'x' else ' ', task.id, task.description)
            }
            out.println()
        }
    }

    private fun add(commandLine: String) {
        val subcommandRest = commandLine.split(" ".toRegex(), 2).toTypedArray()
        val subcommand = subcommandRest[0]
        if (subcommand == "project") {
            addProject(subcommandRest[1])
        } else if (subcommand == "task") {
            val projectTask = subcommandRest[1].split(" ".toRegex(), 2).toTypedArray()
            addTask(projectTask[0], projectTask[1])
        }
    }

    private fun addProject(name: String) {
        tasks[name] = ArrayList()
    }

    private fun addTask(project: String, description: String) {
        val projectTasks = tasks[project]
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project)
            out.println()
            return
        }
        projectTasks.add(Task(nextId(), description, false))
    }

    private fun check(idString: String) {
        setDone(idString, true)
    }

    private fun uncheck(idString: String) {
        setDone(idString, false)
    }

    private fun setDone(idString: String, done: Boolean) {
        val id = Integer.parseInt(idString)
        for ((_, value) in tasks) {
            for (task in value) {
                if (task.id == id.toLong()) {
                    task.isDone = done
                    return
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id)
        out.println()
    }

    private fun help() {
        out.println("Commands:")
        out.println("  show")
        out.println("  add project <project name>")
        out.println("  add task <project name> <task description>")
        out.println("  check <task ID>")
        out.println("  uncheck <task ID>")
        out.println()
    }

    private fun error(command: String) {
        out.printf("I don't know what the command \"%s\" is.", command)
        out.println()
    }

    private fun nextId(): Long {
        return ++lastId
    }

    companion object {
        private val QUIT = "quit"

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val `in` = BufferedReader(InputStreamReader(System.`in`))
            val out = PrintWriter(System.out)
            TaskList(`in`, out).run()
        }
    }
}
