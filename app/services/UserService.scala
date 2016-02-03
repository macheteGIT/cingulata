
package services

import com.google.inject.Inject
import daos.UserDao
import models.User
import org.mongodb.scala.result.UpdateResult

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
class UserService @Inject()(userDao: UserDao) {
  def find(userId: String): Future[User] = userDao.find(userId)

  def findByUsername(username: String): Future[User] = userDao.findByUsername(username)

  def update(user: User): Boolean = userDao.update(user)

  def save(user: User): Unit = userDao.save(user)

}
