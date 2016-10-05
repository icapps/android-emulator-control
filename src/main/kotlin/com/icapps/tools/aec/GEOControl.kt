/*
 * Copyright (c) 2016 iCapps and Nicola Verbeeck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.icapps.tools.aec

/**
 * Interface for controlling geo-location of the emulator
 *
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface GEOControl {

    /**
     * Sends a NMEA 0183 sentence to the device. Currently only '$GPGGA' and '$GPRCM' sentences are supported by the emulator
     *
     * @param [sentence] The NMEA sentence to send
     * @return True on success, false on failure
     */
    fun sendNMEA(sentence: String): Boolean

    /**
     * Sends a simple geo fix to the device, sending only the longitude and latitude
     *
     * @param [longitude] The longitude, in degrees
     * @param [latitude] The latitude, in degrees
     * @return True on success, false on failure
     */
    fun sendFix(longitude: Double, latitude: Double): Boolean

    /**
     * Sends a simple geo fix to the device, sending the longitude, latitude and altitude of the fix
     *
     * @param [longitude] The longitude, in degrees
     * @param [latitude] The latitude, in degrees
     * @param [altitude] THe altitude of the fix, in meters
     * @return True on success, false on failure
     */
    fun sendFix(longitude: Double, latitude: Double, altitude: Double): Boolean

    /**
     * Sends a simple geo fix to the device, sending the longitude, latitude and altitude of the fix along with the
     * number of satellites that provided the fix
     *
     * @param [longitude] The longitude, in degrees
     * @param [latitude] The latitude, in degrees
     * @param [altitude] THe altitude of the fix, in meters
     * @param [numberOfSatellites] The number of satellites that provided the fix
     * @return True on success, false on failure
     */
    fun sendFix(longitude: Double, latitude: Double, altitude: Double, numberOfSatellites: Int): Boolean

}