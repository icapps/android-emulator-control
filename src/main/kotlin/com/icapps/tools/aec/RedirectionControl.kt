package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface RedirectionControl {

    fun addRedirection(protocol: RedirProtocol, hostPort: Int, targetPort: Int): Boolean

    fun removeRedirection(protocol: RedirProtocol, hostPort: Int): Boolean

    fun listRedirections(): List<Redirection>

}

enum class RedirProtocol {
    UDP, TCP
}

data class Redirection(val protocol: RedirProtocol, val hostPort: Int, val targetPort: Int) {
}
