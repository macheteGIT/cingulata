package controllers

import com.google.inject.Inject
import play.api.Play.current
import play.api.cache.Cached
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.ItemService

/**
  * Created by kuzmentsov@gmail.com
  */
class ItemFilterController @Inject()(itemService: ItemService, val messagesApi: MessagesApi) extends Controller {

  /**
   * Returns merged filters template
   * @return merged filters template.
   */
  def filters = Cached("filters") {
    Action.async {
      implicit request => {
        itemService.allCategories.map((categories: Seq[String]) => Ok(views.html.item.filters(categories)))
      }
    }
  }
}
