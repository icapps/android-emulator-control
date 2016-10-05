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

import java.io.InputStream

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class TelnetInputStream(private val delegateStream: InputStream) : InputStream() {

    companion object {
        private val STATE_DATA = 0
        private val STATE_IAC = 1
    }

    private var state: Int = STATE_DATA

    override fun read(): Int {
        var ch: Int
        while (true) {
            ch = delegateStream.read()
            if (ch == -1) return -1

            ch = ch and 0xff

            when (state) {
                STATE_DATA -> {
                    if (ch == 0xFF) state = STATE_IAC
                    else return ch
                }
                STATE_IAC -> {
                    delegateStream.read() //TODO Read 2 items and leave IAC state
                    state = STATE_DATA
                }
            }
        }
    }

}