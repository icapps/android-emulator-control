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

package com.icapps.tools.aec.internal

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.IllegalStateException
import java.net.InetAddress
import java.net.Socket
import java.net.SocketTimeoutException

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class ControlChannel(private val port: Int, private val authToken: String, private val echoDevice: Boolean) {

    companion object {
        private val NO_MORE_DATA_TIMEOUT = 8000
    }

    private lateinit var inputChannel: InputStream
    private lateinit var outputChannel: OutputStream
    private lateinit var communicationClient: Socket
    private var open = false

    fun open() {
        if (open) throw IllegalStateException("Channel is already open")
        openChannel()
        open = true
    }

    fun close() {
        if (!open) return
        closeChannel()
        open = false
    }

    private fun closeChannel() {
        try {
            write("quit")
        } catch (ignored: IOException) {
            //Ignore
        }
        communicationClient.close()
    }

    private fun openChannel() {
        communicationClient = Socket(InetAddress.getLoopbackAddress(), port)

        communicationClient.soTimeout = NO_MORE_DATA_TIMEOUT

        inputChannel = TelnetInputStream(communicationClient.inputStream)
        outputChannel = communicationClient.outputStream

        consumeInitialData()
    }

    fun write(data: String) {
        outputChannel.write(data.toByteArray(Charsets.US_ASCII))
        outputChannel.flush()
        outputChannel.write(0x0D)
        outputChannel.write(0x0A)
        outputChannel.flush()

        //n * (n+1) / 2
        if (echoDevice) {
            val echo = 4 * ((data.length * (data.length + 1)) / 2) + 2
            trueSkip(inputChannel, echo)
        }
    }

    private fun trueSkip(inputChannel: InputStream, echo: Int): Int {
        for (i in 1..echo) {
            inputChannel.read()
        }
        return echo
    }

    fun read(): String {
        var char: Int

        val stringBuilder = StringBuilder()
        while (true) {
            char = inputChannel.read()
            if (char == -1)
                break
            if (char == 0x0D) {
                inputChannel.read()
                break
            }
            stringBuilder.append(char.toChar())
        }
        return stringBuilder.toString().trim()
    }

    private fun consumeInitialData() {
        var mustAuth = false
        while (true) {
            try {
                val line = read()
                if (line.matches(Regex(".* Authentication required"))) {
                    mustAuth = true
                } else if (line == "OK") {
                    break
                }
            } catch (e: SocketTimeoutException) {
                break
            }
        }

        if (mustAuth) {
            doAuth()
        }
    }

    private fun doAuth() {
        write("auth $authToken")
        var authOk = false
        var offendingLine: String? = null
        while (true) {
            try {
                val line = read()
                if (line.matches(Regex("NOK.*"))) {
                    offendingLine = line
                    break
                } else if (line == "OK") {
                    authOk = true
                    break
                }
            } catch (e: SocketTimeoutException) {
                break
            }
        }
        if (!authOk)
            throw IOException("Failed to authenticate: $offendingLine")
    }

}
