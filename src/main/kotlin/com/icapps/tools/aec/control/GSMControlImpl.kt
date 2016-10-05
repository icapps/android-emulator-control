package com.icapps.tools.aec.control

import com.icapps.tools.aec.GSMControl
import com.icapps.tools.aec.GSMState
import com.icapps.tools.aec.GSMStatus
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class GSMControlImpl(private val controlChannel: ControlChannel) : GSMControl {

    companion object {
        private val statusRegex = Regex("([^:]+):\\s*(.*)")
    }

    override fun callToEmulator(fromNumber: String): Boolean {
        controlChannel.write("gsm call $fromNumber")
        return controlChannel.read() == "OK"
    }

    override fun cancelCall(number: String): Boolean {
        controlChannel.write("gsm cancel $number")
        return controlChannel.read() == "OK"
    }

    override fun setDataState(state: GSMState): Boolean {
        controlChannel.write("gsm data ${state.name.toLowerCase()}")
        return controlChannel.read() == "OK"
    }

    override fun setVoiceState(state: GSMState): Boolean {
        controlChannel.write("gsm voice ${state.name.toLowerCase()}")
        return controlChannel.read() == "OK"
    }

    override fun setSignal(rrsi: Int, bitErrorRate: Int): Boolean {
        controlChannel.write("gsm signal $rrsi $bitErrorRate")
        return controlChannel.read() == "OK"
    }

    override fun setSignal(rrsi: Int): Boolean {
        controlChannel.write("gsm signal $rrsi")
        return controlChannel.read() == "OK"
    }

    override fun setSignalStrength(strength: Int): Boolean {
        controlChannel.write("gsm signal-profile $strength")
        return controlChannel.read() == "OK"
    }

    override fun gsmStatus(): GSMStatus {
        controlChannel.write("gsm status")

        var voiceStatus = GSMState.HOME
        var dataStatus = GSMState.HOME
        while (true) {
            val line = controlChannel.read()
            if (line == "OK")
                break
            else if (line.startsWith("KO:"))
                break

            val result = statusRegex.matchEntire(line) ?: continue //TODO log

            val groups = result.groupValues
            when (groups[1].trim().toLowerCase()) {
                "gsm voice state" -> voiceStatus = GSMState.valueOf(groups[2].toUpperCase())
                "gsm data state" -> dataStatus = GSMState.valueOf(groups[2].toUpperCase())
            }
        }
        return GSMStatus(voiceStatus, dataStatus)
    }
}