package controllers

import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.scalatestplus.play._

class ComplexPagesAJAX_CSRF extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  "Controllers" must {

    "give back expected booking page not logged in" in {
      go to s"http://localhost:$port/booking"
      find(cssSelector("error")).foreach(_.text mustBe "Сначала войдите или зарегистрируйтесь")
    }

    go to s"http://localhost:$port/auth"

    "give back expected page" in {
      find(cssSelector("h1")).foreach(_.text mustBe "Войти | Регистрация")
      find(cssSelector("p")).foreach(_.text mustBe "Укажите ваши данные:")
    }

    "login success" in {
      textField(cssSelector("form[class='login'] input[name='username']")).value = "hikita"
      pwdField(cssSelector("form[class='login'] input[name='password']")).value = "pass"
      submit()

      find(className("success")).foreach(_.text mustBe "Вы вошли в свой аккаунт")
      find(className("error")).foreach(_.text mustBe "")
    }

    "login error" in {
      textField(cssSelector("form[class='login'] input[name='username']")).value = "hikita"
      pwdField(cssSelector("form[class='login'] input[name='password']")).value = "pas"
      submit()

      find(className("success")).foreach(_.text mustBe "")
      find(className("error")).foreach(_.text mustBe "Неправильный логин или пароль")
    }

    "register error" in {
      click on className("register")

      textField(cssSelector("form[class='register'] input[name='username']")).value = "hikita"
      pwdField(cssSelector("form[class='register'] input[name='password']")).value = "pass"
      submit()

      find(className("success")).foreach(_.text mustBe "")
      findAll(className("error")).foreach(_.text mustBe "Пользователь с таким именем уже существует")
    }

    "give back expected booking page logged in" in {
      go to s"http://localhost:$port/booking"
      find(cssSelector("h1")).foreach(_.text mustBe "Бронирование столиков")
      find(className("welcome")).foreach(_.text mustBe "Добро пожаловать, hikita!")
      findAll(cssSelector("form p")).map(_.text).toList mustBe List("Укажите ваш телефон:", "Выберите свободный столик:")
    }

    "give back expected menu page" in {
      go to s"http://localhost:9000/menu"
      eventually {
        find(className("sorting")).foreach(_.text mustBe "Сортировать")
        findAll(className("foods")).foreach(_.text must include("Стейк"))
      }
    }
  }
}
