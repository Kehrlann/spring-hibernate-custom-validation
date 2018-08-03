package wf.garnier.spring.hibernatefun.schemavalidation

import org.hibernate.tool.schema.internal.DefaultSchemaFilter
import org.hibernate.tool.schema.spi.SchemaFilterProvider

class CustomSchemaFilterProvider: SchemaFilterProvider {
    override fun getValidateFilter() = CustomSchemaFilter()

    override fun getCreateFilter() = DefaultSchemaFilter.INSTANCE

    override fun getMigrateFilter() = DefaultSchemaFilter.INSTANCE

    override fun getDropFilter() = DefaultSchemaFilter.INSTANCE
}