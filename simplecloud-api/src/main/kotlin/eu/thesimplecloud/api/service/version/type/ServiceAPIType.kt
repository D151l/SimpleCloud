/*
 * MIT License
 *
 * Copyright (C) 2020-2022 The SimpleCloud authors
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

package eu.thesimplecloud.api.service.version.type

/**
 * Created by IntelliJ IDEA.
 * Date: 14.06.2020
 * Time: 10:58
 * @author Frederick Baier
 */
enum class ServiceAPIType(
    val minecraftEdition: MinecraftEdition,
    val serviceVersionType: ServiceVersionType
) {

    /**
     * Represents a jar with the spigot api
     */
    SPIGOT(MinecraftEdition.JAVA, ServiceVersionType.SERVER),

    /**
     * Represents a jar with the bungeecord api
     */
    BUNGEECORD(MinecraftEdition.JAVA, ServiceVersionType.PROXY),

    /**
     * Represents a jar with the velocity api
     */
    VELOCITY(MinecraftEdition.JAVA, ServiceVersionType.PROXY),

    /**
     * Represents a jar with the minestom api
     */
    MINESTOM(MinecraftEdition.JAVA, ServiceVersionType.SERVER);

}