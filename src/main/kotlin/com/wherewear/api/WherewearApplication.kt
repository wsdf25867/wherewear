package com.wherewear.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WherewearApplication

fun main(args: Array<String>) {
    runApplication<WherewearApplication>(*args)
}
