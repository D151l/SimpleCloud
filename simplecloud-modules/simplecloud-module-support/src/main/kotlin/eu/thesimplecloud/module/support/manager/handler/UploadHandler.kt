package eu.thesimplecloud.module.support.manager.handler

import eu.thesimplecloud.module.support.lib.config.UploadConfigLoader
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.util.concurrent.CompletableFuture

/**
 * Created by MrManHD
 * Class create at 07.07.2023 20:02
 */

class UploadHandler {

    private val uploadUrl = UploadConfigLoader().loadConfig().uploadUrl + "/"
    private val url = URL("${uploadUrl}documents")

    fun uploadFile(message: String): CompletableFuture<String> {
        return CompletableFuture.supplyAsync {
            val httpURLConnection = createNewConnection(message)

            val dataOutputStream = DataOutputStream(httpURLConnection.outputStream)
            dataOutputStream.write(message.encodeToByteArray())
            dataOutputStream.close()

            val response = getResponsePath(httpURLConnection)
            this.uploadUrl + response
        }
    }

    private fun createNewConnection(message: String): URLConnection {
        val connection = url.openConnection()
        connection.doOutput = true
        connection.setRequestProperty("User-Agent", "Hastebin Java Api")
        connection.setRequestProperty("Content-Length", message.length.toString())
        connection.useCaches = false
        return connection
    }

    private fun getResponsePath(httpURLConnection: URLConnection): String {
        val bufferedReader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
        var response = bufferedReader.readLine()
        response = response.substring(response.indexOf(":") + 2, response.length - 2)
        return response
    }

}