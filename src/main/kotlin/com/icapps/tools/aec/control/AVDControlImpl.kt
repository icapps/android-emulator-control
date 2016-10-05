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

package com.icapps.tools.aec.control

import com.icapps.tools.aec.AVDControl
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class AVDControlImpl(private val controlChannel: ControlChannel) : AVDControl {

    override fun stop(): Boolean {
        controlChannel.write("avd stop")
        return controlChannel.read() == "OK"
    }

    override fun start(): Boolean {
        controlChannel.write("avd start")
        return controlChannel.read() == "OK"
    }

    override fun restart(): Boolean {
        controlChannel.write("avd start")
        return controlChannel.read() == "OK"
    }

    override fun status(): Boolean {
        controlChannel.write("avd status")
        return controlChannel.read() == "virtual device is running"
    }

    override fun name(): String {
        controlChannel.write("avd name")
        val name = controlChannel.read()
        if (name.startsWith("KO:"))
            return name
        controlChannel.read()
        return name
    }
}