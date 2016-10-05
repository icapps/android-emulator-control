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

import com.icapps.tools.aec.RedirProtocol
import com.icapps.tools.aec.Redirection
import com.icapps.tools.aec.RedirectionControl
import com.icapps.tools.aec.internal.ControlChannel

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
internal class RedirectionControlImpl(private val controlChannel: ControlChannel) : RedirectionControl {

    companion object {
        private val redirRegex = Regex("(tcp|udp):(\\d+)\\s*=>\\s*(\\d+)")
    }

    override fun addRedirection(protocol: RedirProtocol, hostPort: Int, targetPort: Int): Boolean {
        controlChannel.write("redir add ${protocol.name.toLowerCase()}:$hostPort:$targetPort")
        return controlChannel.read() == "OK"
    }

    override fun removeRedirection(protocol: RedirProtocol, hostPort: Int): Boolean {
        controlChannel.write("redir del ${protocol.name.toLowerCase()}:$hostPort")
        return controlChannel.read() == "OK"
    }

    override fun listRedirections(): List<Redirection> {
        controlChannel.write("redir list")

        val redirections: MutableList<Redirection> = mutableListOf()
        while (true) {
            val line = controlChannel.read()
            if (line == "OK")
                break
            else if (line.startsWith("KO:"))
                break
            else if (line.matches(Regex("no active.*")))
                break

            val result = redirRegex.matchEntire(line) ?: continue
            //TODO log
            val groups = result.groupValues

            val protocol = RedirProtocol.valueOf(groups[1].toUpperCase())
            val hostPort = groups[2].toInt()
            val targetPort = groups[3].toInt()
            redirections += Redirection(protocol, hostPort, targetPort)
        }
        return redirections
    }

}