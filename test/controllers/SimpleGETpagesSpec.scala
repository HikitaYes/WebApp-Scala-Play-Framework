package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext.Implicits.global

class SimpleGETpagesSpec extends PlaySpec with GuiceOneAppPerSuite {
  private val dbConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  private val controller = new HomeController(dbConfigProvider, Helpers.stubControllerComponents())

  "Controllers" must {
    "give back expected index page" in {
      val result = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText must include ("Лучший кабачок в своем роде!")
    }

    "give back expected about page" in {
      val result = controller.about.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText must include ("Владелец кабачка:")
      bodyText must include ("Автор сайта:")
    }
  }
}
