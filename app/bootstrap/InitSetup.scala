package bootstrap

import com.google.inject.Inject
import play.Logger


/**
  * Created by kuzmentsov@gmail.com
  */
class InitSetup @Inject()(mongo: MongoConfig) {

  def preMessages(): Unit = {
    Logger.info("Bootstrapping application..")
  }

  def defineIndexes(): Unit = {
  }

  defineIndexes()

}
