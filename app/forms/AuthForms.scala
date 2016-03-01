package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages

/**
  * Created by kuzmentsov@gmail.com.
  */
object AuthForms {

  case class SignupData(email: String, password: String)

  // Signup
  def signupForm(implicit messages: Messages) :Form[SignupData]  = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText.verifying(Messages("error.password.minLength"), password => password.length >= 6)
    )
    (SignupData.apply)(SignupData.unapply))

  // Sign in
  case class LoginData(username:String, password:String)
  val loginForm = Form(mapping(
    "username" -> nonEmptyText,
    "password" -> nonEmptyText
  )(LoginData.apply)(LoginData.unapply))

}
