package daos

import bootstrap.MongoConfig
import com.google.inject.{ImplementedBy, Inject, Singleton}
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.MongoCollection

import models.DataMapping

/**
  * Created by kuzmentsov@gmail.com
 */
@ImplementedBy(classOf[CategoryMappingsDaoImpl])
trait CategoryMappingsDao {
  def set(dataMapping: DataMapping): Unit
}

@Singleton class CategoryMappingsDaoImpl @Inject()(mongo: MongoConfig) extends CategoryMappingsDao {
  val categoryMappings: MongoCollection = mongo.collection("category_mappings")

  override def set(dataMapping: DataMapping): Unit = {
      categoryMappings.findAndModify(
        MongoDBObject("value" -> dataMapping.value),
        MongoDBObject("$set" -> MongoDBObject("reference" -> dataMapping.reference)),
        MongoDBObject(), false, MongoDBObject("value" -> dataMapping.value, "reference" -> dataMapping.reference), false, true
      )
  }
}