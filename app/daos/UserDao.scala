package daos

import com.google.inject.{ImplementedBy, Inject, Singleton}
import com.mongodb.{DBObject, BasicDBObject}
import com.mongodb.casbah.{WriteConcern, MongoCollection}
import com.mongodb.casbah.commons.MongoDBObject
import models.{Session, User}
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.result.UpdateResult
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import services.Mongo

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
@ImplementedBy(classOf[UserDaoImpl])
trait UserDao {
  def find(userId: String): Future[User]

  def findByUsername(username: String): Future[User]

  def update(user: User): Boolean

  def save(user: User): Unit
}

@Singleton
class UserDaoImpl @Inject()(mongo: Mongo) extends UserDao {
  private val users: MongoCollection = mongo.collection("user")

  override def find(userId: String): Future[User] = {
    Future {
      objAsUser(users.findOne(MongoDBObject("_id" -> new ObjectId(userId))).get.asInstanceOf[BasicDBObject])
    }
  }

  override def findByUsername(username: String): Future[User] = {
    Future {
      objAsUser(users.findOne(MongoDBObject("username" -> username)).get.asInstanceOf[BasicDBObject])
    }
  }

  override def update(user: User): Boolean = {
    ???
  }

  override def save(user: User): Unit = {
    users.insert(userAsObj(user))
  }

  private def objAsUser(obj: BasicDBObject): User = {
    User(
      obj.getObjectId("_id").toHexString,
      obj.getString("name"),
      obj.getString("email"),
      obj.getString("username"),
      obj.getString("password"),
      obj.getLong("timestamp")
    )
  }

  private def userAsObj(obj: User): DBObject = {
    MongoDBObject(
      "name" -> obj.name,
      "email" -> obj.email,
      "username" -> obj.username,
      "password" -> obj.password,
      "timestamp" -> System.currentTimeMillis / 1000
    )
  }
}
