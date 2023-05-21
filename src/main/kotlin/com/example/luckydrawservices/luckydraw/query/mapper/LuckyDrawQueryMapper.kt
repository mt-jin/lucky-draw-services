package com.example.luckydrawservices.luckydraw.query.mapper

import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import com.example.luckydrawservices.luckydraw.query.response.LuckyDrawInfoResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers


@Mapper
interface LuckyDrawQueryMapper {
    @Mappings(
        Mapping(source = "luckyDrawInfo.id", target = "id"),
        Mapping(source = "prizeNameList", target = "prizeNames")
    )
    fun toLuckyDrawInfoResponse(luckyDrawInfo: LuckyDrawInfo, prizeNameList: List<String>):LuckyDrawInfoResponse

    companion object {
        val MAPPER: LuckyDrawQueryMapper = Mappers.getMapper(LuckyDrawQueryMapper::class.java)
    }
}