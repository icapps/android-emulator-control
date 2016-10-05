package com.icapps.tools.aec.control

import com.icapps.tools.aec.GEOControl
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class GEOControlImpl(val controlChannel: ControlChannel) : GEOControl {

    override fun sendNMEA(sentence: String): Boolean {
        controlChannel.write("geo nmea $sentence")
        return controlChannel.read() == "OK"
    }

    override fun sendFix(longitude: Double, latitude: Double): Boolean {
        controlChannel.write("geo fix $longitude $latitude")
        return controlChannel.read() == "OK"
    }

    override fun sendFix(longitude: Double, latitude: Double, altitude: Double): Boolean {
        controlChannel.write("geo fix $longitude $latitude $altitude")
        return controlChannel.read() == "OK"
    }

    override fun sendFix(longitude: Double, latitude: Double, altitude: Double, numberOfSatellites: Int): Boolean {
        controlChannel.write("geo fix $longitude $latitude $altitude $numberOfSatellites")
        return controlChannel.read() == "OK"
    }

}