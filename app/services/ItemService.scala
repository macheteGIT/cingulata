package services

import com.google.inject.Inject
import daos.ItemDao
import models.Item

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
class ItemService @Inject()(itemDao: ItemDao) {
  def find(itemId: String): Future[Item] = itemDao.find(itemId)

  def all: Future[Seq[Item]] = itemDao.all

  def allForPage(pageNum: Int): Future[Seq[Item]] = itemDao.allForPage(pageNum)

  def findByHost(host: String): Future[Item] = itemDao.findByHost(host)

  def allCategories: Future[Seq[String]] = itemDao.allCategories

  def allSubCategories: Future[Seq[String]] = itemDao.allSubCategories

  def setCategoryName(oldName: String, newName: String): Future[Int] =  itemDao.setCategoryName(oldName: String, newName: String)

}
