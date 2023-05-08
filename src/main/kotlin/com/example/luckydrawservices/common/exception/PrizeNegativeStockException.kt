package com.example.luckydrawservices.common.exception

class PrizeNegativeStockException(message: String) : ApplicationException(ExceptionCode.NOT_ACTIVE, message)