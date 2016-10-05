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
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface PowerControl {

    fun powerConfig(): PowerConfiguration

    fun setAcStatus(isConnected: Boolean): Boolean

    fun setPowerStatus(powerStatus: PowerStatus): Boolean

    fun setPowerHealthStatus(powerHealthStatus: PowerHealthStatus): Boolean

    fun setBattery(hasBattery: Boolean): Boolean

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