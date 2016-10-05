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

/**
 * Interface for controlling port redirection on the emulator
 *
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface RedirectionControl {

    /**
     * Adds a redirection rule from [hostPort] to [targetPort]
     *
     * @param [protocol] The protocol to forward
     * @param [hostPort] The host port
     * @param [targetPort] The target port
     * @return True on success, false on failure
     */
    fun addRedirection(protocol: RedirProtocol, hostPort: Int, targetPort: Int): Boolean

    /**
     * Remove a redirection
     *
     * @param [protocol] The protocol to remove the forwarding for
     * @param [hostPort] The host port of the rule to remove
     * @return True on success, false on failure
     */
    fun removeRedirection(protocol: RedirProtocol, hostPort: Int): Boolean

    /**
     * Returns the list of active redirections
     */
    fun listRedirections(): List<Redirection>

}

/**
 * Supported redirection protocols
 */
enum class RedirProtocol {
    UDP, TCP
}

/**
 * Data class describing an active redirection
 */
data class Redirection(val protocol: RedirProtocol, val hostPort: Int, val targetPort: Int) {
}
