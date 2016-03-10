package services

import com.google.inject.Inject

import play.api.libs.mailer._

/**
  * Created by kuzmentsov@gmail.com
  */
class MailerService @Inject()(mailerClient: MailerClient) {

  def sendMail(recepients: Seq[String]): Unit = {
    //val cid = "1234"
    val email = Email(
      "RegistrationCompleted",
      "Cingulata <from@email.com>",
      recepients,
      // adds attachment
      // attachments = Seq(
      //   AttachmentFile("attachment.pdf", new File("/some/path/attachment.pdf")),
      //   // adds inline attachment from byte array
      //   AttachmentData("data.txt", "data".getBytes, "text/plain", Some("Simple data"), Some(EmailAttachment.INLINE)),
      //   // adds cid attachment
      //   AttachmentFile("image.jpg", new File("/some/path/image.jpg"), contentId = Some(cid))
      // ),
      // sends text, HTML or both...
      bodyText = Some("A text message"),
      //bodyHtml = Some("""<html><body><p>An <b>html</b> message with cid </p></body></html>""")
      bodyHtml = Some(views.html.emails.mail.render().toString())
    )
    mailerClient.send(email)
  }

}
