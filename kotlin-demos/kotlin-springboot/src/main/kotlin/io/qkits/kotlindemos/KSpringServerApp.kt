package io.qkits.kotlindemos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author: patrick on 2019-08-24
 * @Description:
 */

@SpringBootApplication
class KSpringServerApp

fun main(args:Array<String>){
    runApplication<KSpringServerApp>(*args)
}