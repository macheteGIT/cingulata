package services

import com.google.inject.Inject
import daos.CategoryMappingsDao
import models.DataMapping

/**
  * Created by kuzmentsov@gmail.com
  */
class CategoryMappingService @Inject()(categoryMappingsDao: CategoryMappingsDao) {
  def set(dataMapping: DataMapping): Unit = categoryMappingsDao.set(dataMapping)
}
