package com.icapps.tools.aec.control

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