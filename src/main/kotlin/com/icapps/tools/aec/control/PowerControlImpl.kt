package com.icapps.tools.aec.control

import com.icapps.tools.aec.PowerConfiguration
import com.icapps.tools.aec.PowerControl
import com.icapps.tools.aec.PowerHealthStatus
import com.icapps.tools.aec.PowerStatus
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class PowerControlImpl(private val controlChannel: ControlChannel) : PowerControl {

    companion object {
        private val powerDisplayRegex = Regex("([^:]+):\\s*(.*)")
    }

    override fun powerConfig(): PowerConfiguration {
        controlChannel.write("power display")

        var acStatus = false
        var status = PowerStatus.UNKNOWN
        var health = PowerHealthStatus.UNKNOWN
        var hasBattery = false
        var capacity = -1
        while (true) {
            val line = controlChannel.read()
            if (line == "OK")
                break
            else if (line.startsWith("KO:"))
                break

            val result = powerDisplayRegex.matchEntire(line) ?: continue //TODO log

            val groups = result.groupValues
            when (groups[1].trim().toLowerCase()) {
                "ac" -> acStatus = (groups[2] == "online")
                "status" -> status = PowerStatus.valueOf(groups[2].toUpperCase())
                "health" -> health = PowerHealthStatus.valueOf(groups[2].toUpperCase())
                "present" -> hasBattery = (groups[2] == "true")
                "capacity" -> capacity = groups[2].toInt()
            }
        }
        return PowerConfiguration(acStatus, status, health, hasBattery, capacity)
    }

    override fun setAcStatus(isConnected: Boolean): Boolean {
        controlChannel.write("power ac ${if (isConnected) "on" else "off"}")
        return controlChannel.read() == "OK"
    }

    override fun setPowerStatus(powerStatus: PowerStatus): Boolean {
        controlChannel.write("power status ${powerStatus.name.toLowerCase()}")
        return controlChannel.read() == "OK"
    }

    override fun setPowerHealthStatus(powerHealthStatus: PowerHealthStatus): Boolean {
        controlChannel.write("power health ${powerHealthStatus.name.toLowerCase()}")
        return controlChannel.read() == "OK"
    }

    override fun setBattery(hasBattery: Boolean): Boolean {
        controlChannel.write("power present ${if (hasBattery) "true" else "false"}")
        return controlChannel.read() == "OK"
    }

    override fun setCapacity(capacity: Int): Boolean {
        controlChannel.write("power capacity $capacity")
        return controlChannel.read() == "OK"
    }

}