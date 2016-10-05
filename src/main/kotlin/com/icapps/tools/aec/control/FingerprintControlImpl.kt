package com.icapps.tools.aec.control

import com.icapps.tools.aec.FingerprintControl
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class FingerprintControlImpl(private val controlChannel: ControlChannel) : FingerprintControl {

    override fun touch(index: Int): Boolean {
        controlChannel.write("finger touch $index")
        return controlChannel.read() == "OK"
    }

    override fun removeFinger(): Boolean {
        controlChannel.write("finger remove")
        return controlChannel.read() == "OK"
    }
}