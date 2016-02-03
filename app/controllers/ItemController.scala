package controllers

import play.api.cache.Cached
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import play.api.i18n.MessagesApi
import play.api.Play.current

import com.google.inject.Inject

import services.ItemService
import models.Item

/**
  * Created by kuzmentsov@gmail.com
  */
class ItemController @Inject()(itemService: ItemService, val messagesApi: MessagesApi) extends Controller {

  def test = Action.async {
    implicit request => {
      itemService.findByHost("ALLO").map((item: Item) => {
        println("asadasdad")
        Ok("sadasd")
      })
    }
  }

  /**
   * all items without paging
   */
  def all = Action.async {
    implicit request => {
      itemService.all.map((items: Seq[Item]) => Ok(views.html.item.list(items)))
    }
  }

  /**
   * all items with paging
   */
  def allForPage(pageNum: Int) = Action.async {
    implicit request => {
      itemService.allForPage(pageNum).map((items: Seq[Item]) => Ok(views.html.item.list(items)))
    }
  }

  /**
  * Details
  */
  def details(id: String) = Action.async {
    implicit request => {
      itemService.find(id).map((item: Item) => Ok(views.html.item.details(item)))
    }
  }

  /**
  * Cached Categories page in admin
  */
  def categories = Cached("categories") {
    Action.async {
      implicit request => {
        itemService.allCategories.map((categories: Seq[String]) => Ok(views.html.admin.categories(categories)))
      }
    }
  }

  /**
   * Cached SubCategories page in admin
   */
  def subcategories = Cached("subcategories") {
    Action.async {
      implicit request => {
        itemService.allSubCategories.map((subcategories: Seq[String]) => Ok(views.html.admin.categories(subcategories)))
      }
    }
  }

  /**
   * Cached response of categories page - filters.
   *
   * @return
   */
  def filters = Cached("filters") {
    Action.async {
      implicit request => {
        itemService.allCategories.map((categories: Seq[String]) => Ok(views.html.item.filters(categories)))
      }
    }
  }

}
