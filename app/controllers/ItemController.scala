package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import play.api.i18n.MessagesApi

import com.google.inject.Inject

import services.ItemService
import models.Item

/**
  * Created by kuzmentsov@gmail.com
  */
class ItemController @Inject()(itemService: ItemService, val messagesApi: MessagesApi) extends Controller {

  /**
   *
   * @param pageNum: Int - number of page.
   * @return merged template of list items page.
   */
  def allForPage(pageNum: Int) = Action.async {
    implicit request => {
      itemService.allForPage(pageNum).map((items: Seq[Item]) => Ok(views.html.item.list(items)))
    }
  }

  /**
   *
   * @param id: String - item's id.
   * @return item details merged template.
   */
  def details(id: String) = Action.async {
    implicit request => {
      itemService.find(id).map((item: Item) => Ok(views.html.item.details(item)))
    }
  }
}
