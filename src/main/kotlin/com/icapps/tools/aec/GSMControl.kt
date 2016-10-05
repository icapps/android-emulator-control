package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface GSMControl {

    fun callToEmulator(fromNumber: String): Boolean

    fun cancelCall(number: String): Boolean

    fun setDataState(state: GSMState): Boolean

    fun setVoiceState(state: GSMState): Boolean

    fun gsmStatus(): GSMStatus

    fun setSignal(rrsi: Int): Boolean

    fun setSignal(rrsi: Int, bitErrorRate: Int): Boolean

    fun setSignalStrength(strength: Int): Boolean
}

enum class GSMState {
    UNREGISTERED, HOME, ROAMING, SEARCHING, DENIED
}

data class GSMStatus(val voiceStatus: GSMState, val dataStatus: GSMState) {
}