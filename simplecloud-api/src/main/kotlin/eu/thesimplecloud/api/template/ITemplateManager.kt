package eu.thesimplecloud.api.template


interface ITemplateManager {


    /**
     * Adds or updates the specified template
     */
    fun updateTemplate(template: ITemplate)

    /**
     * Removes the template found by the specified name
     */
    fun removeTemplate(name: String)

    /**
     * Returns a list containing all registered templates
     */
    fun getAllTemplates() : Collection<ITemplate>

    /**
     * Returns the first template found by the specified name
     */
    fun getTemplateByName(name: String): ITemplate? = getAllTemplates().firstOrNull { it.getName().equals(name, true) }

    /**
     * Clears the cache
     */
    fun clearCache()

}