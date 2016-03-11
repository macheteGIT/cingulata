package controllers

import java.util.UUID

import actions.SecureAction
import com.github.t3hnar.bcrypt._
import com.google.inject.Inject
import com.mongodb.MongoWriteException
import forms.AuthForms
import models.{Session, User}
import play.api.Play.current
import play.api.data.Form
import play.api.i18n.Messages.Implicits._
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.{SessionService, UserService}

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
class AuthController @Inject()(userService: UserService,
                               sessionService: SessionService,
                               secureAction: SecureAction,
                               val messagesApi: MessagesApi) extends Controller {

  def signup = Action.async(BodyParser { implicit request => parse.form(AuthForms.signupForm, onErrors = { errorForm: Form[AuthForms.SignupData] => BadRequest(errorForm.errorsAsJson) })(request) }) { implicit request =>
    val signupData = request.body
    val user = User(
      UUID.randomUUID().toString,
      signupData.email,
      signupData.password.bcrypt,
      System.currentTimeMillis())
    userService.save(user)
    Future(Ok)
  }

  def signin = Action.async(BodyParser { implicit request => parse.form(AuthForms.loginForm, onErrors = { errorForm: Form[AuthForms.LoginData] => BadRequest(errorForm.errorsAsJson) })(request) }) { implicit request =>
    val loginData = request.body
    userService.findByUsername(loginData.username).map((user: User) => {
      if (loginData.password.isBcrypted(user.password)) {
        val sessionId: String = UUID.randomUUID().toString
        val currentTimeMillis: Long = System.currentTimeMillis()
        val session: Session = models.Session(
          sessionId,
          user.id,
          request.remoteAddress,
          request.headers.get("User-Agent").get,
          currentTimeMillis,
          currentTimeMillis
        )
        sessionService.save(session)
        Ok(sessionId).withCookies(Cookie("sessionId", sessionId))
      } else {
        Unauthorized
      }
    }).recoverWith {
      case e: IllegalStateException => Future {
        Forbidden
      }
    }
  }

  def logout = secureAction { implicit request =>
    sessionService.delete(request.sessionId)
    Ok.discardingCookies(DiscardingCookie("sessionId"))
  }
}
