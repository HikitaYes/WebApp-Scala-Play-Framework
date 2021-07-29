package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}
import models.Tables._
import org.mindrot.jbcrypt.BCrypt

class Model(db: Database)(implicit ec: ExecutionContext) {
  private def findUser(username: String) = {
    db.run(Users.filter(_.username === username).result)
  }

  def validateUser(username: String, password: String): Future[Boolean] = {
    val matches = findUser(username)
    matches.map(_.exists(userRow => BCrypt.checkpw(password, userRow.password)))
  }

  def createUser(username: String, password: String): Future[Boolean] = {
    findUser(username).map(_.isEmpty).flatMap { isNameFree =>
      if (isNameFree)
        db.run(Users += UsersRow(-1, username, BCrypt.hashpw(password, BCrypt.gensalt())))
          .map(addCount => addCount > 0)
      else Future.successful(false)
    }
  }

  def getDishes: Future[Seq[DishesRow]] = {
    db.run(
      (for {
        dish <- Dishes
      } yield dish).result
    )
  }

  def getFreeTable: Future[Seq[Int]] = {
    db.run(Booking.sortBy(_.id).result)
      .map(bookingRows => bookingRows.filter(_.idUser.isEmpty).map(_.id))
  }

  def setTableBooking(idTable: Int, phoneNumber: String, username: String) = {
    db.run(Users.filter(_.username === username).map(_.phoneNumber).update(Some(phoneNumber)))
    db.run(Users.filter(_.username === username).map(_.id).result).flatMap { ids =>
      db.run(Booking.filter(_.id === idTable).map(_.idUser).update(Some(ids.head)))
    }
  }
}