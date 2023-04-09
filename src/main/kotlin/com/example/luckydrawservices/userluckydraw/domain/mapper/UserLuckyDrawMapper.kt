package com.example.luckydrawservices.userluckydraw.domain.mapper

import com.example.luckydrawservices.userluckydraw.infrastructure.UserLuckyDrawEntity
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface UserLuckyDrawMapper {
    @Mappings(
        Mapping(target = "id", ignore = true)
    )
    fun toUserLuckyDrawEntity(userLuckyDraw: UserLuckyDraw): UserLuckyDrawEntity

    companion object {
        val MAPPER: UserLuckyDrawMapper = Mappers.getMapper(UserLuckyDrawMapper::class.java)
    }
}