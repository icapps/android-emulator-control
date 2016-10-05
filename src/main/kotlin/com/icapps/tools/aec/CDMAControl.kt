package com.icapps.tools.aec

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
interface CDMAControl {

    fun setCDMASource(source: CDMASource): Boolean

    fun dumpPRL(version: Int): Boolean

}

enum class CDMASource {
    RUIM, NV
}