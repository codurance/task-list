import java.io._

import org.scalatest.{BeforeAndAfter, FlatSpec}

class TaskListSpec extends FlatSpec with BeforeAndAfter {

  var applicationThread: Thread = null

  val inStream = new PipedOutputStream()
  val inWriter = new PrintWriter(inStream, true)

  val outStream = new PipedInputStream()
  val outReader = new BufferedReader(new InputStreamReader(outStream))

  before {
    val in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)))
    val out = new PipedOutputStream(outStream)
    scala.Console.withOut(out) {
      scala.Console.withIn(in) {
        applicationThread = new Thread(new TaskList)
        applicationThread.start()
      }
    }
  }

  after {
    if (applicationThread != null && applicationThread.isAlive) {
      applicationThread.interrupt()
      throw new IllegalStateException("The application is still running.")
    }
  }

  "A task list" should "work" in {

    executeCommand("show")

    executeCommand("add project secrets")
    executeCommand("add task secrets Eat more donuts.")
    executeCommand("add task secrets Destroy all humans.")

    executeCommand("show")
    readLines(
      "secrets",
      "    [ ] 1: Eat more donuts.",
      "    [ ] 2: Destroy all humans.",
      "")

    executeCommand("add project training")
    executeCommand("add task training Four Elements of Simple Design")
    executeCommand("add task training SOLID")
    executeCommand("add task training Coupling and Cohesion")
    executeCommand("add task training Primitive Obsession")
    executeCommand("add task training Outside-In TDD")
    executeCommand("add task training Interaction-Driven Design")

    executeCommand("check 1")
    executeCommand("check 3")
    executeCommand("check 5")
    executeCommand("check 6")

    executeCommand("show")
    readLines(
      "secrets",
      "    [x] 1: Eat more donuts.",
      "    [ ] 2: Destroy all humans.",
      "",
      "training",
      "    [x] 3: Four Elements of Simple Design",
      "    [ ] 4: SOLID",
      "    [x] 5: Coupling and Cohesion",
      "    [x] 6: Primitive Obsession",
      "    [ ] 7: Outside-In TDD",
      "    [ ] 8: Interaction-Driven Design",
      "")

    executeCommand("quit")
  }

  def executeCommand(command: String): Unit = {
    read("» ")
    inWriter.println(command)
  }

  def readLines(expectedOutput: String*): Unit = {
    expectedOutput.foreach { line =>
      read(line + System.lineSeparator())
    }
  }

  def read(expectedOutput: String): Unit = {
    val length = expectedOutput.length()
    val buffer = new Array[Char](length)
    outReader.read(buffer, 0, length)
    assert(String.valueOf(buffer) == expectedOutput)
  }
}
