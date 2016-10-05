package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface GEOControl {

    fun sendNMEA(sentence: String): Boolean

    fun sendFix(longitude: Double, latitude: Double): Boolean

    fun sendFix(longitude: Double, latitude: Double, altitude: Double): Boolean

    fun sendFix(longitude: Double, latitude: Double, altitude: Double, numberOfSatellites: Int): Boolean

}