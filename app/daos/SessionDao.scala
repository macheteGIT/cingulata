package daos

import scala.concurrent.ExecutionContext.Implicits.global

import com.google.inject.{ImplementedBy, Inject, Singleton}
import com.mongodb.{DBObject, BasicDBObject}
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoCollection}
import org.bson.types.ObjectId

import scala.concurrent.Future

import services.Mongo

import models.{Session}

/**
  * Created by kuzmentsov@gmail.com
  */
@ImplementedBy(classOf[MongoSessionDao])
trait SessionDao {
  def find(sessionId: String): Future[Session]

  def findByUserId(userId: String): Future[Session]

  def save(session: Session): Unit

  def delete(sessionId: String): Boolean

  //def updateLastActivity(sessionId: String): Future[UpdateResult]
}

@Singleton
class MongoSessionDao @Inject()(mongo: Mongo) extends SessionDao {
  private val sessions: MongoCollection = mongo.collection("session")

  override def find(sessionId: String): Future[Session] = {
    Future {
      objAsSession(sessions.findOne(MongoDBObject("_id" -> new ObjectId(sessionId))).get.asInstanceOf[BasicDBObject])
    }
  }

  override def findByUserId(userId: String): Future[Session] = {
    Future {
      objAsSession(sessions.findOne(MongoDBObject("userId" -> userId)).get.asInstanceOf[BasicDBObject])
    }
  }

  override def save(session: Session): Unit = {
    sessions.insert(sessionAsObj(session))
  }

  override def delete(sessionId: String): Boolean = {
    sessions.remove(MongoDBObject("_id" -> new ObjectId(sessionId))).isUpdateOfExisting
  }

/*  override def updateLastActivity(sessionId: String): Future[UpdateResult] = {
    sessions.findA(equal("_id", sessionId), set("lastActivity", System.currentTimeMillis())).head()
  }*/

  private def objAsSession(obj: BasicDBObject): Session = {
    Session(
      obj.getObjectId("_id").toHexString,
      obj.getString("userId"),
      obj.getString("ip"),
      obj.getString("userAgent"),
      obj.getLong("timestamp"),
      obj.getLong("lastActivity")
    )
  }

  private def sessionAsObj(obj: Session): DBObject = {
    MongoDBObject(
      "userId" -> obj.userId,
      "ip" -> obj.ip,
      "userAgent" -> obj.userAgent,
      "timestamp" -> System.currentTimeMillis / 1000,
      "lastActivity" -> System.currentTimeMillis / 1000
    )
  }
}

