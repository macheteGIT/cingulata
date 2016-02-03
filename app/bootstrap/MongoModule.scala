package bootstrap

import com.google.inject.AbstractModule

/**
  * Created by kuzmentsov@gmail.com
  */
class MongoModule extends AbstractModule {
  protected def configure(): Unit = {
    bind(classOf[InitSetup]).asEagerSingleton()
  }
}