package com.icapps.tools.aec.control

import com.icapps.tools.aec.AVDControl
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class AVDControlImpl(private val controlChannel: ControlChannel) : AVDControl {

    override fun stop(): Boolean {
        controlChannel.write("avd stop")
        return controlChannel.read() == "OK"
    }

    override fun start(): Boolean {
        controlChannel.write("avd start")
        return controlChannel.read() == "OK"
    }

    override fun restart(): Boolean {
        controlChannel.write("avd start")
        return controlChannel.read() == "OK"
    }

    override fun status(): Boolean {
        controlChannel.write("avd status")
        return controlChannel.read() == "virtual device is running"
    }

    override fun name(): String {
        controlChannel.write("avd name")
        val name = controlChannel.read()
        if (name.startsWith("KO:"))
            return name
        controlChannel.read()
        return name
    }
}