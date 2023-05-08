package com.example.luckydrawservices.common.exception

class PrizesNotFoundException(message: String) : ApplicationException(ExceptionCode.NOT_FOUND, message)