package controllers.admin

import com.google.inject.Inject
import models.DataMapping
import play.api.Play.current
import play.api.cache.Cached
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.{CategoryMappingService, ItemService}

/**
  * Created by kuzmentsov@gmail.com
  */
class ItemCategoryController @Inject()(
                                        categoryMappingService: CategoryMappingService,
                                        itemService: ItemService,
                                        val messagesApi: MessagesApi) extends Controller {

  /**
   * Returns merged categories template
   * @return merged categories page template.
   */
  def categories = Action.async {
      implicit request => {
        itemService.allCategories.map((categories: Seq[String]) => Ok(views.html.admin.categories(categories)))
      }
  }

  /**
   * Updates category. Inserts new key-value pair in admin.
   * @param oldName: String - old name of category.
   * @param newName: String - new name of category.
   * @return
   */
  def updateCategory(oldName: String, newName: String) = Action.async {
    implicit request => {
      categoryMappingService.set(DataMapping(oldName, newName))
      itemService.setCategoryName(oldName, newName).map((amount: Int) => Ok(amount.toString))
    }
  }
}
