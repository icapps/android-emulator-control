package com.icapps.tools.aec.control

import com.icapps.tools.aec.SMSControl
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class SMSControlImpl(private val controlChannel: ControlChannel) : SMSControl {

    override fun send(phoneNumber: String, message: String): Boolean {
        controlChannel.write("sms send $phoneNumber $message")
        return controlChannel.read() == "OK"
    }
}