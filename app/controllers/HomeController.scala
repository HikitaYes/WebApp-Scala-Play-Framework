package controllers

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
      Redirect(routes.HomeController.auth).flashing("success" -> "Вы вошли в свой аккаунт")
    }.getOrElse(Redirect(routes.HomeController.login))
  }

  def register = Action { implicit request =>
    request.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password = args("password").head
      Redirect(routes.HomeController.auth).flashing("success" -> "Вы зарегестрировали свой аккаунт")
    }.getOrElse(Redirect(routes.HomeController.login))

  }
}
