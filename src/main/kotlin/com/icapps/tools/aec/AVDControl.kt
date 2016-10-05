package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface AVDControl {

    fun stop(): Boolean

    fun start(): Boolean

    fun restart(): Boolean

    fun status(): Boolean

    fun name(): String

}