package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import models.UsersModel

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext


@Singleton
class HomeController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
  extends BaseController with HasDatabaseConfigProvider[JdbcProfile] {

  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def menu = Action { implicit request =>
    Ok(views.html.menu())
  }

  def auth() = Action { implicit request =>
    Ok(views.html.auth())
  }

  def login = Action { implicit request =>
    request.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password = args("password").head
      if (UsersModel.validateUser(username, password)) {
        Redirect(routes.HomeController.auth).withSession("username" -> username).flashing("success" -> "Вы вошли в свой аккаунт")
      } else {
        Redirect(routes.HomeController.auth).flashing("error" -> "Неправильный логин или пароль")
      }
    }.getOrElse(Redirect(routes.HomeController.auth).flashing("error" -> "Произошла ошибка. Войдите еще раз"))
  }

  def register = Action { implicit request =>
    request.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password = args("password").head
      if (UsersModel.createUser(username, password)) {
        Redirect(routes.HomeController.auth).flashing("success" -> "Пользователь успешно зарегистрирован")
      } else {
        Redirect(routes.HomeController.auth).flashing("error" -> "Пользователь с таким именем уже существует")
      }
    }.getOrElse(Redirect(routes.HomeController.auth))
  }

  def logout = Action {
    Redirect(routes.HomeController.auth).withNewSession
  }
}
