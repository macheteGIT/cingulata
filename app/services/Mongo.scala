package services

import com.google.inject.{Inject, Singleton}
import com.mongodb.casbah.{MongoDB, MongoConnection}
import play.Logger
import play.api.Configuration
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
@Singleton
class Mongo @Inject()(applicationLifecycle: ApplicationLifecycle, configuration: Configuration) {

  //init of connection
  val mongoConn = MongoConnection(configuration.getString("mongo.db.host").get, configuration.getInt("mongo.db.port").get)

  //selecting collection from properties
  val collection: MongoDB = mongoConn(configuration.getString("mongo.db.name").get)

  applicationLifecycle.addStopHook(() => {
    Logger.warn("Closing Mongo connection")
    Future.successful(mongoConn.close())
  })
}
