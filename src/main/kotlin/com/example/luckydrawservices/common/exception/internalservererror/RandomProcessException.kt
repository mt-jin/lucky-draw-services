package com.example.luckydrawservices.common.exception.internalservererror

import com.example.luckydrawservices.common.exception.ApplicationException
import com.example.luckydrawservices.common.exception.ExceptionCode

class RandomProcessException(message: String) : ApplicationException(ExceptionCode.INTERNAL_SERVER_ERROR, message)