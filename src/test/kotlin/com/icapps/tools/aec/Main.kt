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

    println(channel.avdControl.name())

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