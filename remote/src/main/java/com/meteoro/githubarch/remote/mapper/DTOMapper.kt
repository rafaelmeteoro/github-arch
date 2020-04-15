package com.meteoro.githubarch.remote.mapper

interface DTOMapper<in DTO, out DATA> {
    fun mapToData(dto: DTO): DATA
}