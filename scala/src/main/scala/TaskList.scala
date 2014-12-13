import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

case class Task(id: Long, description: String, var done: Boolean = false)

class TaskList extends Runnable {

  private val tasks = mutable.HashMap[String, Seq[Task]]()

  override def run(): Unit = {
    var finished = false
    while(!finished) {
      StdIn.readLine("» ") match {
        case "quit" => finished = true
        case commandLine => execute(commandLine)
      }
    }
  }

  private def execute(commandLine: String): Unit = {
    val commandParts = commandLine.split("""\s""", 2)
    val command = commandParts(0)
    command match {
      case "" => ()
      case "help" => help()
      case "show" => show()
      case "add" => add(commandParts(1))
      case "check" => check(commandParts(1))
      case "uncheck" => uncheck(commandParts(1))
      case _ => showError(command)
    }
  }

  private def help(): Unit = {
    Console.println(
      """Commands:
        |  show
        |  add project <project name>
        |  add task <project name> <task description>
        |  check <task ID>
        |  uncheck <task ID>
      """.stripMargin)
  }

  private def show(): Unit = {
    tasks foreach { case (project, projectTasks) =>
      Console.println(project)
      projectTasks foreach { task =>
        val checkbox = if (task.done) "[x]" else "[ ]"
        Console.println(s"    $checkbox ${task.id}: ${task.description}")
      }
      Console.println()
    }
  }

  private def add(commandLine: String): Unit = {
    val commandWords = commandLine.split("""\s""", 2)
    val command = commandWords(0)
    command match {
      case "project" => addProject(commandWords(1))
      case "task" => {
        val projectTask = commandWords(1).split("""\s""", 2)
        addTask(projectTask(0), projectTask(1))
      }
      case _ => showError(commandLine)
    }
  }

  private def addProject(name: String): Unit = {
    tasks += name -> Seq[Task]()
  }

  private def addTask(project: String, description: String): Unit = {
    tasks.get(project).map { projectTasks =>
      tasks.put(project, projectTasks :+ Task(nextId, description))
    }.getOrElse {
      Console.println(Console.RED + "Could not find a project with the name " + project + Console.WHITE)
    }
  }

  private def nextId: Long = {
    (0L +: tasks.values.flatten.map(_.id).toList).max + 1L
  }

  private def check(idString: String): Unit = setDone(idString, true)

  private def uncheck(idString: String): Unit = setDone(idString, false)

  private def setDone(idString: String, done: Boolean): Unit = {
    tasks.values.flatten.find(_.id == idString.toLong).foreach { task =>
      task.done = done
    }
  }

  private def showError(command: String): Unit = {
    Console.println(Console.RED + "Unknown command: " + command + Console.WHITE)
  }
}

object TaskList {
  def main( args:Array[String] ): Unit = {
    (new TaskList).run()
  }
}