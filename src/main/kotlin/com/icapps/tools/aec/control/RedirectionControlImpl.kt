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