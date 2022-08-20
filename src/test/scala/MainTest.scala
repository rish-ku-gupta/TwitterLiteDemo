package test.scala
import org.scalatest.funsuite.AnyFunSuite
import main.scala.Main
import java.util.Calendar

class MainTest extends AnyFunSuite  {
  val uname = "Bob"
  val msg = "I love the weather today"
  val funame = "Alice"
  val dt = Calendar.getInstance.getTimeInMillis

  Main.timeline.addBinding(uname, "test" + "qqq" + dt)
  Main.timeline.addBinding(funame, "test" + "qqq" + dt)

  // Success Test Cases
  test("Success - Post message") {
    assert(Main.addmessage(uname, msg, Calendar.getInstance.getTimeInMillis) == "Success")
  }
  test("Success - Display messages") {
    assert(Main.displaymessages(uname, "read") == "Success")
  }
  test("Success - Add follows") {
    assert(Main.addfollows(uname, funame) == "Success")
  }
  test("Success - Display Wall") {
    assert(Main.displaywall(uname) == "Success")
  }

  // Failure Test Cases
  test("Failed - Display messages") {
    assert(Main.displaymessages("Rishabh", "read") == "Success")
  }
  test("Failed - Add follows") {
    assert(Main.addfollows("Rishabh", funame) == "Success")
  }
  test("Failed - Display Wall") {
    assert(Main.displaywall("Rishabh") == "Success")
  }

}
