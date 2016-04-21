package controllers.admin

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
class ItemSubCategoryController @Inject()(itemService: ItemService, val messagesApi: MessagesApi) extends Controller {

  /**
   * Returns merged subcategories page template.
   * @return merged subcategories page template.
   */
  def subcategories = Cached("subcategories") {
    Action.async {
      implicit request => {
        itemService.allSubCategories.map((subcategories: Seq[String]) => Ok(views.html.admin.categories(subcategories)))
      }
    }
  }
}
