package controllers

import models.UsersModel

import javax.inject._
import play.api._
import play.api.mvc._


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def menu = Action {
    Ok(views.html.menu())
  }

  def auth = Action { implicit request =>
    Ok(views.html.auth())
  }

  def login = Action { implicit request =>
    request.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password = args("password").head
      if (UsersModel.validateUser(username, password)) {
        Redirect(routes.HomeController.auth).flashing("success" -> "Вы вошли в свой аккаунт")
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
        Redirect(routes.HomeController.auth).flashing("success" -> "Вы зарегистрировали свой аккаунт")
      } else {
        Redirect(routes.HomeController.auth).flashing("error" -> "Пользователь с таким именем уже существует")
      }
    }.getOrElse(Redirect(routes.HomeController.auth))
  }
}
