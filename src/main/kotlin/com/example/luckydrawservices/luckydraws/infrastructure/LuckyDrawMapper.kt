package com.example.luckydrawservices.luckydraws.infrastructure

import com.example.luckydrawservices.luckydraws.query.response.LuckyDrawInfoResponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers


@Mapper
interface LuckyDrawMapper {
    fun toLuckyDrawInfoResponse(luckyDrawInfo: LuckyDrawInfo):LuckyDrawInfoResponse

    companion object {
        val MAPPER: LuckyDrawMapper = Mappers.getMapper(LuckyDrawMapper::class.java)
    }
}