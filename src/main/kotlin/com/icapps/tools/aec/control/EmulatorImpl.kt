package com.icapps.tools.aec.control

import com.icapps.tools.aec.*
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class EmulatorImpl(port: Int, authToken: String) : Emulator {

    private val controlChannel: ControlChannel
    override val powerControl: PowerControl
    override val redirectionControl: RedirectionControl
    override val avdControl: AVDControl
    override val fingerprintControl: FingerprintControl
    override val geoControl: GEOControl
    override val smsControl: SMSControl
    override val cdmaControl: CDMAControl
    override val gsmControl: GSMControl

    init {
        controlChannel = ControlChannel(port, authToken)
        powerControl = PowerControlImpl(controlChannel)
        redirectionControl = RedirectionControlImpl(controlChannel)
        avdControl = AVDControlImpl(controlChannel)
        fingerprintControl = FingerprintControlImpl(controlChannel)
        geoControl = GEOControlImpl(controlChannel)
        smsControl = SMSControlImpl(controlChannel)
        cdmaControl = CDMAControlImpl(controlChannel)
        gsmControl = GSMControlImpl(controlChannel)
    }

    override fun connect() {
        controlChannel.open()
    }

    override fun disconnect() {
        controlChannel.close()
    }

    override fun rotateLeft() {
        controlChannel.write("rotate")
    }

    override fun crash() {
        controlChannel.write("crash")
        controlChannel.read()
        disconnect()
    }

    override fun kill() {
        controlChannel.write("kill")
        controlChannel.read()
        disconnect()
    }

}