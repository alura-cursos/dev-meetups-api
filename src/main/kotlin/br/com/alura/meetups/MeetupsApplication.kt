package br.com.alura.meetups

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableAsync
class MeetupsApplication

fun main(args: Array<String>) {
	runApplication<MeetupsApplication>(*args)
}