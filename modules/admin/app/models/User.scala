package models

import play.api.libs.json.Json

/**
  * Created by kuzmentsov@gmail.com
  */
case class User(
                 id: String,
                 email: String,
                 password: String,
                 registeredOn: Long
               )

object User {
  implicit val userFormat = Json.format[User]
}
