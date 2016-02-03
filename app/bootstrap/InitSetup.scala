package bootstrap

import com.google.inject.Inject
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Sorts._
import play.Logger
import services.Mongo


/**
  * Created by kuzmentsov@gmail.com
  */
class InitSetup @Inject()(mongo: Mongo) {

  def preMessages(): Unit = {
    Logger.info("Bootstrapping application..")
  }

  def defineIndexes(): Unit = {
  }

  defineIndexes()

}
