package com.example.luckydrawservices.common.exception

class LuckyDrawStatusException(message: String) : ApplicationException(ExceptionCode.NOT_ACTIVE, message)