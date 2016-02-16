package services

import com.google.inject.Inject
import daos.{ItemFilterDao}

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
class ItemFilterService @Inject()(itemFilterDao: ItemFilterDao) {
  def findByFilter(): Future[String] = itemFilterDao.findByFilter
}
