package models

import scala.collection.mutable

object UsersModel {
  private val users = mutable.Map[String, String]("a" -> "a")

  def validateUser(username: String, password: String): Boolean = {
    users.get(username).contains(password)
  }

  def createUser(username: String, password: String): Boolean = {
    if (users.contains(username)) false else {
    users(username) = password
    true
    }
  }
}
