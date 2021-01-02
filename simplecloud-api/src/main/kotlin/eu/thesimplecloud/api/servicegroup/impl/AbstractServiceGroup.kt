/*
 * MIT License
 *
 * Copyright (C) 2020 The SimpleCloud authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package eu.thesimplecloud.api.servicegroup.impl

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.version.ServiceVersion
import eu.thesimplecloud.api.servicegroup.ICloudServiceGroup

abstract class AbstractServiceGroup(
    private val name: String,
    @Volatile private var templateName: String,
    @Volatile private var maxMemory: Int,
    @Volatile private var maxPlayers: Int,
    @Volatile private var minimumOnlineServiceCount: Int,
    @Volatile private var maximumOnlineServiceCount: Int,
    @Volatile private var maintenance: Boolean,
    private val static: Boolean,
    @Volatile private var percentToStartNewService: Int,
    @Volatile private var wrapperName: String?,
    serviceVersion: ServiceVersion,
    private val startPriority: Int,
    @Volatile private var permission: String?,
    @Volatile private var stateUpdating: Boolean = true
) : ICloudServiceGroup {

    @Volatile private var serviceVersion = serviceVersion.name

    override fun getName(): String = this.name

    override fun getPermission(): String? {
        return this.permission
    }

    override fun setPermission(permission: String?) {
        this.permission = permission
    }

    override fun getTemplateName(): String = this.templateName

    override fun setTemplateName(name: String) {
        this.templateName = name
    }

    override fun getMaxMemory(): Int = this.maxMemory

    override fun setMaxMemory(memory: Int) {
        this.maxMemory = memory
    }

    override fun getMaxPlayers(): Int = this.maxPlayers

    override fun setMaxPlayers(maxPlayers: Int) {
        this.maxPlayers = maxPlayers
    }

    override fun getMinimumOnlineServiceCount(): Int = this.minimumOnlineServiceCount

    override fun setMinimumOnlineServiceCount(count: Int) {
        this.minimumOnlineServiceCount = count
    }

    override fun getMaximumOnlineServiceCount(): Int = this.maximumOnlineServiceCount

    override fun setMaximumOnlineServiceCount(count: Int) {
        this.maximumOnlineServiceCount = count
    }

    override fun isInMaintenance(): Boolean = this.maintenance

    override fun setMaintenance(maintenance: Boolean) {
        this.maintenance = maintenance
    }

    override fun isStatic(): Boolean = this.static

    override fun getPercentToStartNewService(): Int = this.percentToStartNewService

    override fun setPercentToStartNewService(percentage: Int) {
        this.percentToStartNewService = percentage
    }

    override fun getWrapperName(): String? = this.wrapperName

    override fun setWrapperName(name: String?) {
        this.wrapperName = name
    }

    override fun setServiceVersion(serviceVersion: ServiceVersion) {
        this.serviceVersion = serviceVersion.name
    }

    override fun getServiceVersion(): ServiceVersion {
        return CloudAPI.instance.getServiceVersionHandler().getServiceVersionByName(serviceVersion)!!
    }

    override fun isStateUpdatingEnabled(): Boolean {
        return this.stateUpdating
    }

    override fun setStateUpdating(stateUpdating: Boolean) {
        this.stateUpdating = stateUpdating
    }

    override fun getStartPriority(): Int = this.startPriority

}