package eu.thesimplecloud.base.wrapper.process.queue

import eu.thesimplecloud.base.wrapper.process.CloudServiceProcess
import eu.thesimplecloud.base.wrapper.process.ICloudServiceProcess
import eu.thesimplecloud.base.wrapper.startup.Wrapper
import eu.thesimplecloud.launcher.startup.Launcher
import eu.thesimplecloud.lib.network.packets.wrapper.PacketIOUpdateWrapperInfo
import eu.thesimplecloud.lib.service.ICloudService
import eu.thesimplecloud.lib.service.ServiceState
import eu.thesimplecloud.lib.wrapper.IWritableWrapperInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class CloudServiceProcessQueue(val maxSimultaneouslyStartingServices: Int) {

    private val queue = LinkedBlockingQueue<ICloudServiceProcess>()
    private val startingServices = ArrayList<ICloudServiceProcess>()

    fun addToQueue(cloudService: ICloudService) {
        Launcher.instance.consoleSender.sendMessage("wrapper.service.queued", "Service %NAME%", cloudService.getName(), " is now queued.")
        val cloudServiceProcess = CloudServiceProcess(cloudService)
        this.queue.add(cloudServiceProcess)
        Wrapper.instance.cloudServiceProcessManager.registerServiceProcess(cloudServiceProcess)
        Wrapper.instance.updateUsedMemory()
    }


    fun startThread() {
        thread(start = true, isDaemon = true) {
            while (true) {
                startingServices.removeIf { cloudServiceProcess -> cloudServiceProcess.getCloudService().getState() === ServiceState.LOBBY || cloudServiceProcess.getCloudService().getState() === ServiceState.CLOSED }
                if (queue.isNotEmpty()) {
                    if (startingServices.size < maxSimultaneouslyStartingServices) {
                        val cloudServiceProcess = queue.poll()
                        thread { cloudServiceProcess.start() }
                        startingServices.add(cloudServiceProcess)
                    }
                }
                Thread.sleep(200)
            }
        }
    }

    fun clearQueue() {
        queue.forEach { Wrapper.instance.cloudServiceProcessManager.unregisterServiceProcess(it) }
        queue.clear()
    }

}