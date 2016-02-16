package controllers

import com.google.inject.Inject
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.{ItemFilterService, ItemService}

/**
  * Created by kuzmentsov@gmail.com
  */
class ItemFilterController @Inject()(itemService: ItemService, itemFilterService: ItemFilterService, val messagesApi: MessagesApi) extends Controller {

  /**
   * Returns merged filters template
   * @return merged filters template.
   */
  def filters = Action.async {
    implicit request => {
      itemService.allCategories.map((categories: Seq[String]) => Ok(views.html.item.filters(categories)))
    }
  }

  def itemsByFilterAsJson = Action.async {
    implicit request => {
      itemFilterService.findByFilter.map((json: String) => Ok(json).as("application/json"))
    }
  }

}
