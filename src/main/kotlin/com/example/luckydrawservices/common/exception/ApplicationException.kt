package com.example.luckydrawservices.common.exception

abstract class ApplicationException(val code: ExceptionCode, message: String) : RuntimeException(message)