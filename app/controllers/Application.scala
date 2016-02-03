package controllers

import play.api.mvc._

/**
  * Created by kuzmentsov@gmail.com
  */
class Application extends Controller {

  def index = Action {
    Ok(views.html.main())
  }

}