  package controllers

import actions.SecureAction
import com.google.inject.Inject
import models.Item
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.{SessionService, ItemService}

/**
  * Created by kuzmentsov@gmail.com
  */
class AdminController @Inject()(itemService: ItemService, sessionService: SessionService, SecuredAction: SecureAction) extends Controller {

  def hostsAvailable = Action.async {
    implicit request => {
      itemService.findByHost("ALLO").map((item: Item) => {
        println("asadasdad")
        Ok("sadasd")
      })
    }
  }

  def userActivity = Action.async {
    implicit request => {
      sessionService.find().map(sessions => Ok(views.html.admin.UserActivity(sessions)))
    }
  }
}
