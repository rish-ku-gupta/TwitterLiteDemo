package main.scala

import java.util.Calendar
import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.util.control.Breaks.{break, breakable}

object Main {
  // Multimap for capturing users and their messages along with message posting time.
  val timeline = new mutable.HashMap[String, mutable.Set[String]] with mutable.MultiMap[String, String]

  // Multimap for capturing users and their respective followers.
  val follow = new mutable.HashMap[String, mutable.Set[String]] with mutable.MultiMap[String, String]

  def main(args: Array[String]): Unit = {
    /**
     * As the name suggests, the execution is controlled from within this function.
     * User input is accepted from console, and validated against pre-defined actions.
     * Data is stored only as long as the application is running.
     */

    startapplication()

    breakable {
      /* The Application will keep running unless user stops it explicitly.
         User can type in exit as a command or force close the program directly.
      */
      while (true) {
        val a = scala.io.StdIn.readLine()
        if (a.trim.toLowerCase == "exit") break

        else if (a.contains(" -> ")) {
          val uname = a.substring(0, a.indexOf("->") - 1)
          val msg = a.substring(a.indexOf("->") + 3)
          if (!timeline.keySet.contains(uname)) println(s"This user does not exist. A new user with username $uname has now been created.")
          addmessage(uname, msg, Calendar.getInstance.getTimeInMillis)
        }

        else if (!a.contains(" ")) displaymessages(a.trim, "read")

        else if (a.contains(" follows ")) {
          val uname = a.substring(0, a.indexOf("follows") - 1)
          val funame = a.substring(a.indexOf("follows") + 8)
          addfollows(uname, funame)
          println(uname + " has successfully started following " + funame)
        }

        else if (a.contains(" wall")) displaywall(a.substring(0, a.indexOf("wall") - 1))

        else println("Invalid command. Please refer to the instructions printed on top on how to use this application.")
      }
    }
  }

  def startapplication(): Unit = {
    /**
     * Just printing messages that will guide the user on how to interact with the application.
     */

    println("Welcome to TwitterLite!! Starting New Session...")
    println("Session Started! Please follow these usage instructions:")
    println("1. For posting a message, use the command format: <user name> -> <message>")
    println("2. For reading a user's messages, use the command format: <user name>")
    println("3. For following a user, use the command format: <user name> follows <another user name>")
    println("4. For looking at a user's wall, use the command format: <user name> wall")
    println("5. Type exit to close the session anytime.")
  }

  def addmessage(uname: String, msg: String, dt: Long): String = {
    /**
     * Registers/Captures the messages posted by a user.
     * Automatically registers a new user if the username is not found in the system.
     */

    timeline.addBinding(uname, msg + "qqq" + dt)
    "Success"
  }

  def displaymessages(uname: String, action: String): String = {
    /**
     * Prints all the messages posted by a user while using the application.
     * Checks if the user is present in the system or not and prints messages accordingly.
     */

    if(timeline.keySet.contains(uname)){
      val v = timeline.get(uname)
      for (i <- v) {
        for (j <- i) {
          var msg = j.substring(0, j.indexOf("qqq"))
          val dt = j.substring(j.indexOf("qqq") + 3).toLong
          val duration = TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance.getTimeInMillis - dt)
          if (duration < 60) msg = msg + " (" + duration + " seconds ago)"
          else if (duration >= 60 && duration < 120) msg = msg + " (" + "1 minute ago)"
          else msg = msg + " (" + TimeUnit.SECONDS.toMinutes(duration) + " minutes ago)"
          if (action == "read") println(msg)
          else println(uname + " - " + msg)
        }
      }
      "Success"
    }
    else {
      println("User does not exist. Please check provided username in the command.")
      "Fail"
    }

  }

  def addfollows(uname: String, funame: String): String = {
    /**
     * Allows a user to follow another user.
     * This action results in messages of all the followed users appearing on the wall of the requesting user.
     */

    if(timeline.keySet.contains(uname) && timeline.keySet.contains(funame)) {
      follow.addBinding(uname, funame)
      "Success"
    }
    else if(uname == funame) {
      println("Follower and Followed usernames cannot be same")
      "Fail"
    }
    else {
      println("One or both users do not exist. Please check provided usernames in the command.")
      "Fail"
    }
  }

  def displaywall(uname: String): String = {
    /**
     * Displays the messages of the user in question along with all the other users that this user is following.
     */

    if(timeline.keySet.contains(uname)) {
      displaymessages(uname, "wall")
      val v = follow.get(uname)
      for (i <- v) {
        for (j <- i) {
          displaymessages(j, "wall")
        }
      }
      "Success"
    }
    else {
      println("User does not exist. Please check provided username in the command.")
      "Fail"
    }
  }

}
