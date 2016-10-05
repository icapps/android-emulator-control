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

import com.icapps.tools.aec.CDMAControl
import com.icapps.tools.aec.CDMASource
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class CDMAControlImpl(private val controlChannel: ControlChannel) : CDMAControl {

    override fun setCDMASource(source: CDMASource): Boolean {
        controlChannel.write("cdma ssource ${source.name}")
        return controlChannel.read() == "OK"
    }

    override fun dumpPRL(version: Int): Boolean {
        controlChannel.write("cdma prl_version $version")
        return controlChannel.read() == "OK"
    }
}