package models

import play.api.libs.json.Json

/**
  * Created by kuzmentsov@gmail.com
  */
case class Item(
                 id: String,
                 host: String,
                 url: String,
                 title: String,
                 category: String,
                 subcategory: String,
                 price: Double,
                 features: Map[String, String]
               )

object Item {
  implicit val itemFormat = Json.format[Item]
}
