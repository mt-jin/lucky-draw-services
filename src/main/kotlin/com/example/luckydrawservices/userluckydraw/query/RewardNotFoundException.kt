package com.example.luckydrawservices.userluckydraw.query

class UserLuckyDrawNotFoundException(message: String) : ApplicationException(ExceptionCode.NOT_FOUND, message)