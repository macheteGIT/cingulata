
package services

import daos.UserDao
import models.User
import services.{ItemFilterService, ItemService, MailerService}

import org.mongodb.scala.result.UpdateResult
import com.google.inject.Inject

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
class UserService @Inject()(userDao: UserDao, mailerService: MailerService) {
  def find(userId: String): Future[User] = userDao.find(userId)

  def findByUsername(username: String): Future[User] = userDao.findByUsername(username)

  def update(user: User): Boolean = userDao.update(user)

  def save(user: User): Unit = {
    mailerService.sendMail(Seq(user.email))
    userDao.save(user)
  }
}
