package eu.thesimplecloud.base.manager.setup

import eu.thesimplecloud.clientserverapi.lib.json.JsonData
import eu.thesimplecloud.launcher.console.setup.ISetup
import eu.thesimplecloud.launcher.console.setup.annotations.SetupQuestion
import eu.thesimplecloud.launcher.startup.Launcher
import eu.thesimplecloud.lib.utils.Downloader
import eu.thesimplecloud.lib.service.ServiceVersion
import java.io.File

class ProxyJarSetup(val proxyFile: File) : ISetup {

    @SetupQuestion(0, "manager.setup.proxy-jar.question", "Which proxy version do you want to use? (Bungeecord, Waterfall, Travertine, Hexacord)")
    fun setup(answer: String): Boolean {
        val serviceVersion = JsonData.fromObject(answer.toUpperCase()).getObjectOrNull(ServiceVersion::class.java)
        if (serviceVersion == null) {
            Launcher.instance.consoleSender.sendMessage("manager.setup.proxy-jar.version-invalid", "The specified version is invalid.")
            return false
        }
        Launcher.instance.consoleSender.sendMessage("manager.setup.proxy-jar.downloading", "Downloading proxy...")
        Downloader().userAgentDownload(serviceVersion.downloadLink, proxyFile.path)
        return true
    }

}
