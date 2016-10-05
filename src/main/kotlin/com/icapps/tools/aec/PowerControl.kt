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