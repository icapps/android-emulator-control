package com.icapps.tools.aec

import com.icapps.tools.aec.Emulator
import com.icapps.tools.aec.control.EmulatorImpl

/**
 * @author Nicola Verbeeck
 * @date 05/10/16.
 */
class EmulatorFactory {

    companion object {
        @JvmStatic fun create(port: Int, authSecret: String): Emulator {
            return EmulatorImpl(port, authSecret)
        }
    }

}