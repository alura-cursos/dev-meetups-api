package br.com.alura.meetups.logger

import org.aspectj.lang.ProceedingJoinPoint

import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class AppLogger {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Pointcut("execution(* br.com.alura.meetups..services..*(..))")
    fun services() {
    }

    @Pointcut("execution(* br.com.alura.meetups.api..controllers..*(..))")
    fun restApi() {
    }

    @Around("services() || restApi()")
    @Throws(Throwable::class)
    fun profile(joinPoint: ProceedingJoinPoint?): Any? {
        return joinPoint?.let {
            val clazz = it.signature.declaringTypeName
            val method = it.signature.name
            val args = it.args
            try {
                it.proceed()
            } finally {
                if (args != null && args.isNotEmpty())
                    logger.info("$clazz - $method() args=${args.first()}") else logger.info("$clazz - $method()")
            }
        }
    }

}