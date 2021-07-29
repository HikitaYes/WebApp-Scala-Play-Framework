package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}
import models.Tables._
import org.mindrot.jbcrypt.BCrypt

class Model(db: Database)(implicit ec: ExecutionContext) {
  def validateUser(username: String, password: String): Future[Boolean] = {
    val matches = db.run(Users.filter(_.username === username).result)
    matches.map(_.exists(userRow => BCrypt.checkpw(password, userRow.password)))
  }

  def createUser(username: String, password: String): Future[Boolean] = {
    db.run(Users += UsersRow(-1, username, BCrypt.hashpw(password, BCrypt.gensalt())))
      .map(addCount => addCount > 0)
  }

  def getDishes: Future[Seq[DishesRow]] = {
    db.run(
      (for {
        dish <- Dishes
      } yield dish).result
    )
  }
}