package com.example.luckydrawservices.common.exception

data class ExceptionRepresentation(
    val code: ExceptionCode,
    val message: String?,
    val details: Map<String, String?> = emptyMap(),
)
