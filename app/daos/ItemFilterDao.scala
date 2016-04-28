package daos

import bootstrap.MongoConfig
import com.google.inject.{ImplementedBy, Inject, Singleton}
import com.mongodb.casbah.{MongoCollection}
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
 */
@ImplementedBy(classOf[ItemFilterDaoImpl]) trait ItemFilterDao {
  def findByFilter(): Future[String]
}

@Singleton class ItemFilterDaoImpl @Inject()(mongo: MongoConfig) extends ItemFilterDao {
  val items: MongoCollection = mongo.collection("items")

  def findByFilter(): Future[String] = {
    val pagingRange = 50
    Future {
      items.find().skip(pagingRange).limit(pagingRange).toList.map(obj => com.mongodb.util.JSON.serialize(obj)).mkString("[", ",", "]")
    }
  }

}
