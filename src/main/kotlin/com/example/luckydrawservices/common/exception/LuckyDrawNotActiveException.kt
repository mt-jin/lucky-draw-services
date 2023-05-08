package com.example.luckydrawservices.common.exception

class LuckyDrawNotActiveException(message: String) : ApplicationException(ExceptionCode.NOT_ACTIVE, message)