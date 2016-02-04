package services

import com.google.inject.{Inject, Singleton}
import com.mongodb.ServerAddress
import com.mongodb.casbah._
import play.Logger
import play.api.Configuration
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
@Singleton
class Mongo @Inject()(applicationLifecycle: ApplicationLifecycle, configuration: Configuration) {

  val path = "mongodb://beaver:wai3Ughi@ds045521.mongolab.com:45521/cingulata"
  val uri = MongoClientURI(path)
  val server = new ServerAddress(configuration.getString("mongo.db.host").get, configuration.getInt("mongo.db.port").get)

  val credentials = MongoCredential.createCredential(
    configuration.getString("mongo.db.username").get,
    "admin",
    configuration.getString("mongo.db.password").get.toCharArray
  )

  //val client = MongoClient(List(server), List(credentials))
  val client = MongoClient(uri)
  //init of connection
  //val mongoConn = MongoConnection(configuration.getString("mongo.db.host").get, configuration.getInt("mongo.db.port").get)

  //selecting collection from properties
  val collection: MongoDB = client(configuration.getString("mongo.db.name").get)

  applicationLifecycle.addStopHook(() => {
    Logger.warn("Closing Mongo connection")
    Future.successful(client.close())
  })
}
