  package controllers

import com.google.inject.Inject
import models.Item
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.ItemService

/**
  * Created by kuzmentsov@gmail.com
  */
class AdminController @Inject()(itemService: ItemService) extends Controller {

  def hostsAvailable = Action.async {
    implicit request => {
      itemService.findByHost("ALLO").map((item: Item) => {
        println("asadasdad")
        Ok("sadasd")
      })
    }
  }

}
