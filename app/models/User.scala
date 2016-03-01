package models

/**
  * Created by kuzmentsov@gmail.com
  */
case class User(
                 _id: String,
                 email: String,
                 password: String,
                 registeredOn: Long
               )

object User {

  import play.api.libs.json.Json

  implicit val userFormat = Json.format[User]
}
