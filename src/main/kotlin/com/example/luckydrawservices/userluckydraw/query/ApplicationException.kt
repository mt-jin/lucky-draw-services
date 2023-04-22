package com.example.luckydrawservices.userluckydraw.query

abstract class ApplicationException(val code: ExceptionCode, message: String) : RuntimeException(message)