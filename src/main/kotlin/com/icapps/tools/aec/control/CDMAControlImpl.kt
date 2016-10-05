package com.icapps.tools.aec.control

import com.icapps.tools.aec.CDMAControl
import com.icapps.tools.aec.CDMASource
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class CDMAControlImpl(private val controlChannel: ControlChannel) : CDMAControl {

    override fun setCDMASource(source: CDMASource): Boolean {
        controlChannel.write("cdma ssource ${source.name}")
        return controlChannel.read() == "OK"
    }

    override fun dumpPRL(version: Int): Boolean {
        controlChannel.write("cdma prl_version $version")
        return controlChannel.read() == "OK"
    }
}