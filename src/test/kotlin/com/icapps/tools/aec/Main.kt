package com.icapps.tools.aec

import com.icapps.tools.aec.control.EmulatorImpl
import java.io.File

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
fun main(args: Array<String>) {
    System.setProperty("java.net.preferIPv4Stack", "true")

    val channel = EmulatorImpl(5554, File("${System.getProperty("user.home")}/.emulator_console_auth_token").readText())
    channel.connect()

    channel.rotateLeft()
    channel.rotateLeft()
    channel.rotateLeft()
    channel.rotateLeft()

    checkNotNull(channel.avdControl)
    checkNotNull(channel.cdmaControl)
    checkNotNull(channel.fingerprintControl)
    checkNotNull(channel.geoControl)
    checkNotNull(channel.gsmControl)
    checkNotNull(channel.redirectionControl)
    checkNotNull(channel.smsControl)

    channel.redirectionControl.listRedirections().forEach(::println)

    println(channel.powerControl.powerConfig())
    channel.powerControl.setAcStatus(true)
    channel.powerControl.setBattery(true)
    channel.powerControl.setCapacity(100)
    channel.powerControl.setPowerHealthStatus(PowerHealthStatus.GOOD)
    channel.powerControl.setPowerStatus(PowerStatus.FULL)

    println(channel.powerControl.powerConfig())

    channel.disconnect()
}