package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface Emulator {

    fun connect()

    fun disconnect()

    fun rotateLeft()

    fun crash()

    fun kill()

    val powerControl: PowerControl

    val redirectionControl: RedirectionControl

    val avdControl: AVDControl

    val fingerprintControl: FingerprintControl

    val geoControl: GEOControl

    val smsControl: SMSControl

    val cdmaControl: CDMAControl

    val gsmControl: GSMControl
}
