package models

/**
  * Created by kuzmentsov@gmail.com
  */
case class Item(
                 _id: String,
                 host: String,
                 url: String,
                 title: String,
                 category: String,
                 subcategory: String,
                 price: Double,
                 features: Map[String, String]
               )

object Item {

  import play.api.libs.json.Json

  implicit val itemFormat = Json.format[Item]
}
