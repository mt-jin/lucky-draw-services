package com.example.luckydrawservices.common.exception.conflict

import com.example.luckydrawservices.common.exception.ApplicationException
import com.example.luckydrawservices.common.exception.ExceptionCode

class PrizeNegativeStockException(message: String) : ApplicationException(ExceptionCode.NOT_ACTIVE, message)