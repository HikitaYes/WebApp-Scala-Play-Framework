package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models.UsersModel
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class HomeController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
  extends BaseController with HasDatabaseConfigProvider[JdbcProfile] {

  private val model = new UsersModel(db)

  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def menu = Action.async { implicit request =>
    model.getDishes.map(dishes =>
      Ok(views.html.menu(dishes))
    )
  }

  def auth() = Action { implicit request =>
    Ok(views.html.auth())
  }

  def login = Action.async { implicit request =>
    request.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password = args("password").head
      model.validateUser(username, password).map { userExists =>
        if (userExists) {
          Redirect(routes.HomeController.auth).withSession("username" -> username).flashing("success" -> "Вы вошли в свой аккаунт")
        } else {
          Redirect(routes.HomeController.auth).flashing("error" -> "Неправильный логин или пароль")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.HomeController.auth).flashing("error" -> "Произошла ошибка. Войдите еще раз")))
  }

  def register = Action.async { implicit request =>
    request.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password = args("password").head
      model.createUser(username, password).map { userCreated =>
        if (userCreated) {
          Redirect(routes.HomeController.auth).flashing("success" -> "Пользователь успешно зарегистрирован")
        } else {
          Redirect(routes.HomeController.auth).flashing("error" -> "Пользователь с таким именем уже существует")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.HomeController.auth)))
  }

  def logout = Action {
    Redirect(routes.HomeController.auth).withNewSession
  }

  def booking = Action { implicit request =>
    request.session.get("username").map { username =>

      Ok(views.html.booking())
    }.getOrElse(Redirect(routes.HomeController.auth).flashing("error" -> "Сначала войдите или зарегистрируйтесь"))
  }
}
