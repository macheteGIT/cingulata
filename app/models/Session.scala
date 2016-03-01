package models

import play.api.libs.json.Json

/**
  * Created by kuzmentsov@gmail.com
  */
case class Session(
                    id: String,
                    userId: String,
                    ip: String,
                    userAgent: String,
                    timestamp: Long,
                    lastActivity: Long
                  )

object Session {
  implicit val userFormat = Json.format[Session]
}
