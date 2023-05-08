package com.example.luckydrawservices.common.exception

enum class ExceptionCode(val statusCode: Int) {
    BAD_REQUEST(400),
    NOT_FOUND(404),
    NOT_ACTIVE(409),
    INTERNAL_SERVER_ERROR(500)
}