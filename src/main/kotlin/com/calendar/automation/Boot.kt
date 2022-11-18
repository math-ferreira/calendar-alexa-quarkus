package com.calendar.automation

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.annotations.QuarkusMain

@QuarkusMain
object Boot {

    @JvmStatic
    fun main(args: Array<String>) {
        Quarkus.run(*args)
    }
}