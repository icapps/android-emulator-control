package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface SMSControl {

    fun send(phoneNumber: String, message: String): Boolean

}