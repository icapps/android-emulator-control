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
 * Interface for controlling GSM features of the emulator
 *
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface GSMControl {

    /**
     * Initiates a phone call coming from the given number to the device
     *
     * @param [fromNumber] The number to call from
     * @return True on success, false on failure
     */
    fun callToEmulator(fromNumber: String): Boolean

    /**
     * Cancels a call, either incoming or outgoing
     *
     * @param [number] The phone number of the incoming or outgoing call
     * @return True on success, false on failure
     */
    fun cancelCall(number: String): Boolean

    /**
     * Sets the data state of the device
     *
     * @param [state] The new data state of the device
     * @return True on success, false on failure
     */
    fun setDataState(state: GSMState): Boolean

    /**
     * Sets the voice state of the device
     *
     * @param [state] The new voice state of the device
     * @return True on success, false on failure
     */
    fun setVoiceState(state: GSMState): Boolean

    /**
     * Reads the gsm status from the device
     *
     * @return The GSM status. If a field was not returned by the device, it is set to [GSMState.HOME]
     */
    fun gsmStatus(): GSMStatus

    /**
     * Sets the RRSI of the device
     *
     * @param [rrsi] The rrsi to set. Must be in the range [0...31] or 99 when the rssi is unknown
     */
    fun setSignal(rrsi: Int): Boolean

    /**
     * Sets the RRSI and bit error rate of the device
     *
     * @param [rrsi] The rrsi to set. Must be in the range [0...31] or 99 when the rssi is unknown
     * @param [bitErrorRate] The hit error rate. Must be in the range [0...7] or 99 when the rate is unknown
     */
    fun setSignal(rrsi: Int, bitErrorRate: Int): Boolean

    /**
     * Sets the signal strength of the device
     *
     * @param [strength] The strength of the signal. Must be in the range [0...4]
     */
    fun setSignalStrength(strength: Int): Boolean
}

/**
 * Possible GSM data and voice states
 */
enum class GSMState {
    UNREGISTERED, HOME, ROAMING, SEARCHING, DENIED
}

/**
 * Data class containing the status for both voice and data
 */
data class GSMStatus(val voiceStatus: GSMState, val dataStatus: GSMState) {
}