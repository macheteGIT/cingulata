package services

import com.google.inject.Inject

import play.api.libs.mailer._

/**
  * Created by kuzmentsov@gmail.com
  */
class MailerService @Inject()(mailerClient: MailerClient) {

  def sendMail(recepients: Seq[String]): Unit = {
    val email = Email(
      "RegistrationCompleted",
      "Cingulata <admin@cingulata.org>",
      recepients,
      bodyText = Some("A text message"),
      bodyHtml = Some(views.html.emails.mail.render().toString())
    )
    mailerClient.send(email)
  }

}
