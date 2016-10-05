package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface FingerprintControl {

    fun touch(index: Int) : Boolean

    fun removeFinger() : Boolean

}