package com.example.luckydrawservices.common.exception.notfound

import com.example.luckydrawservices.common.exception.ApplicationException
import com.example.luckydrawservices.common.exception.ExceptionCode

class PrizesNotFoundException(message: String) : ApplicationException(ExceptionCode.NOT_FOUND, message)