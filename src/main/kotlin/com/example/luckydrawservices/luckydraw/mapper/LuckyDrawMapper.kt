package com.example.luckydrawservices.luckydraw.mapper

import com.example.luckydrawservices.luckydraw.domain.model.LuckyDrawWithItems
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import com.example.luckydrawservices.prize.query.model.PrizeInfo
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface LuckyDrawMapper {

    @Mappings(
        Mapping(source = "prizes", target = "items"),
    )
    fun toLuckyDrawWithItems(
        luckyDraw: LuckyDrawEntity,
        prizes: List<PrizeInfo>
    ): LuckyDrawWithItems

    fun mapToLuckyDrawEntity(luckyDrawWithItems: LuckyDrawWithItems): LuckyDrawEntity

    companion object {
        val MAPPER: LuckyDrawMapper = Mappers.getMapper(LuckyDrawMapper::class.java)
    }
}