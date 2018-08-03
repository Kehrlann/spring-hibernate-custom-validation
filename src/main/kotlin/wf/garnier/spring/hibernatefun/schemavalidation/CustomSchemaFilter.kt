package wf.garnier.spring.hibernatefun.schemavalidation

import org.hibernate.mapping.Table
import org.hibernate.tool.schema.internal.DefaultSchemaFilter

class CustomSchemaFilter: DefaultSchemaFilter() {
    override fun includeTable(table: Table?): Boolean {
        if(table?.name == "application_user") {
            return false
        }
        return super.includeTable(table)
    }
}