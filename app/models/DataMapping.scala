package models

/**
 * Class used to manage data, that can gain another value, called mapping.
 * Maps one value to reference.
 * Created by kuzmentsov@gmail.com on 11.02.16.
 */

case class DataMapping(value: String, reference: String)

object DataMapping {

  import play.api.libs.json.Json

  implicit val dataMappingFormat = Json.format[DataMapping]
}