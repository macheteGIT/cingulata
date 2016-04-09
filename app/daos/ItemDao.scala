package daos

import bootstrap.MongoConfig
import com.google.inject.{ImplementedBy, Inject, Singleton}
import com.mongodb.BasicDBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoCollection}
import org.bson.types.ObjectId

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import scala.collection.JavaConversions._

import models.Item

/**
  * Created by kuzmentsov@gmail.com
 */
@ImplementedBy(classOf[ItemDaoImpl]) trait ItemDao {
  protected val pagingRange = 5

  def find(itemId: String): Future[Item]

  def allForPage(pageNum: Int): Future[Seq[Item]]

  def findByHost(host: String): Future[Item] = ???

  def allCategories: Future[Seq[String]]

  def allSubCategories: Future[Seq[String]]

  def allHosts: Future[List[String]] = ???

  def setCategoryName(oldName: String, newName: String): Future[Int]
}

@Singleton class ItemDaoImpl @Inject()(mongo: MongoConfig) extends ItemDao {
  val items: MongoCollection = mongo.collection("items")

  def find(itemId: String): Future[Item] = {
    Future {
      objAsItem(items.findOne(MongoDBObject("_id" -> new ObjectId(itemId))).get.asInstanceOf[BasicDBObject])
    }
  }

  def allForPage(pageNum: Int): Future[Seq[Item]] = {
    Future {
      items.find().skip(pageNum * pagingRange).limit(pagingRange).map(x => objAsItem(x.asInstanceOf[BasicDBObject])).toList
    }
  }

  def allCategories: Future[Seq[String]] = {
    Future {
      items.distinct("category").map(_.toString)
    }
  }

  override def allSubCategories: Future[Seq[String]] = {
    Future {
      items.distinct("subcategory").map(_.toString)
    }
  }

  override def setCategoryName(oldName: String, newName: String): Future[Int] = {
    Future {
      val bulk = items.initializeOrderedBulkOperation
      bulk.find(MongoDBObject("category" -> oldName)).update(MongoDBObject(
        "$set" -> MongoDBObject("category" -> newName)
      ))
      bulk.execute().getModifiedCount getOrElse -1
    }
  }

  private def objAsItem(obj: BasicDBObject): Item = {
    Item(
      obj.getObjectId("_id").toHexString,
      obj.getString("host"),
      obj.getString("url"),
      obj.getString("title"),
      obj.getString("category"),
      obj.getString("subcategory"),
      obj.getDouble("price"),
      obj.get("features").asInstanceOf[BasicDBObject].map(x => (x._1, x._2.toString)).toMap
    )
  }
}
