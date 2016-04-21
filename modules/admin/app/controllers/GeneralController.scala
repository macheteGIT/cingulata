package controllers.admin

import com.google.inject.Inject
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.{SessionService, ItemService}

/**
  * Created by kuzmentsov@gmail.com
  */
class GeneralController @Inject()(itemService: ItemService, sessionService: SessionService) extends Controller {

  def userActivity = Action.async {
    implicit request => {
      sessionService.find().map(sessions => Ok(views.html.admin.UserActivity(sessions)))
    }
  }

}
