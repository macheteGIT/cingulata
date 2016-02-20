package controllers

import com.google.inject.Inject
import play.api.mvc._
import services.ItemFilterService

import play.api.libs.concurrent.Execution.Implicits.defaultContext

class ItemSimilarityController @Inject()(itemFilterService: ItemFilterService) extends Controller {
	
	/**
	* Retrieves JSON Array of similar items
	*
	* @param comparableItemId: String - id of Item to compare.
    * @return Json Array of similar Items.
	*/
	def getSimilarTo(comparableItemId: String) = Action.async {
    implicit request => itemFilterService.findByFilter.map((json: String) => Ok(json).as("application/json"))
	}
}