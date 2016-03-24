package services

import com.google.inject.Inject
import daos.{UserDao, SessionDao}
import models.{User, Session}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

/**
  * Created by kuzmentsov@gmail.com
  */
class SessionService @Inject()(sessionDao: SessionDao,
                               userDao: UserDao) {
  def find(sessionId: String): Future[Session] = sessionDao.find(sessionId)

  def find(): Future[Seq[Session]] = sessionDao.find()

  def findUserBySessionId(sessionId: String): Future[User] = find(sessionId).flatMap(session =>
    userDao.find(session.userId)
  )

  def findByUserId(userId: String): Future[Session] = sessionDao.findByUserId(userId)

  def save(session: Session) = sessionDao.save(session)

  def delete(sessionId: String) = sessionDao.delete(sessionId)

  //def updateLastActivity(sessionId: String) = sessionDao.updateLastActivity(sessionId)
}

