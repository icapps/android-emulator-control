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
 * Interface for controlling power settings of the emulator
 *
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface PowerControl {

    /**
     * Reads the current power configuration from the device
     *
     * @return The power configuration of the device
     */
    fun powerConfig(): PowerConfiguration

    /**
     * Sets the ac status of the device, either connected or disconnected
     *
     * @param [isConnected] True for when the device is connected, false if not
     * @return True on success, false on failure
     */
    fun setAcStatus(isConnected: Boolean): Boolean

    /**
     * Sets the power status of the device
     *
     * @param [powerStatus] The power status to set
     * @return True on success, false on failure
     */
    fun setPowerStatus(powerStatus: PowerStatus): Boolean

    /**
     * Sets the health status of the battery of the device
     *
     * @param [powerHealthStatus] The power health status to set
     * @return True on success, false on failure
     */
    fun setPowerHealthStatus(powerHealthStatus: PowerHealthStatus): Boolean

    /**
     * Sets the flag indicating if the device has a batter or not
     *
     * @param [hasBattery] Flag indicating if the device has a battery connected or not
     * @return True on success, false on failure
     */
    fun setBattery(hasBattery: Boolean): Boolean

    /**
     * Sets the device capacity in percentage (0 ... 100)
     *
     * @param [capacity] The power percentage of the battery. Must fall within [0 ... 100]
     * @return True on success, false on failure
     */
    fun setCapacity(capacity: Int): Boolean

}

enum class PowerStatus {
    UNKNOWN, CHARGING, DISCHARGING, NOT_CHARGING, FULL
}

enum class PowerHealthStatus {
    UNKNOWN, GOOD, OVERHEAT, DEAD, OVERVOLTAGE, FAILURE
}

data class PowerConfiguration(val acStatus: Boolean, val status: PowerStatus, val health: PowerHealthStatus, val hasBattery: Boolean, val capacity: Int) {
}