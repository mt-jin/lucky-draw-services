package com.example.luckydrawservices.common.exception

import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode.valueOf
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

private val logger = KotlinLogging.logger {}

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(exception: ApplicationException):ResponseEntity<ExceptionRepresentation>{
        return mapToResponseEntity(exception.code, exception.message ?: "").also {
            if (it.statusCode.is5xxServerError) {
                logger.error { "Application Exception: ${exception.message}" }
            } else {
                logger.warn { "Application Exception: ${exception.message}" }
            }
        }
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ExceptionRepresentation> {
        logger.error(exception) { "Handled exception: ${exception.message}" }
        return mapToResponseEntity(ExceptionCode.INTERNAL_SERVER_ERROR, "Exception occurred")
    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(exception: Throwable): ResponseEntity<ExceptionRepresentation> {
        logger.error(exception) { "Internal error: ${exception.message}" }
        return mapToResponseEntity(ExceptionCode.INTERNAL_SERVER_ERROR, "Internal Server Error")
    }

    private fun mapToResponseEntity(
        code: ExceptionCode,
        message: String,
        errorDetails: Map<String,String?> = emptyMap(),
    ): ResponseEntity<ExceptionRepresentation>{
        return ResponseEntity(
            ExceptionRepresentation(code, message, errorDetails), HttpHeaders(), valueOf(code.statusCode)
        )
    }
}