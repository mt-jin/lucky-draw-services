package com.example.luckydrawservices.common.exception

class LuckyDrawNotFoundException(message: String) : ApplicationException(ExceptionCode.NOT_FOUND, message)