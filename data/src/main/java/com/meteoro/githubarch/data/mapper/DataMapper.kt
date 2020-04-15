package com.meteoro.githubarch.data.mapper

interface DataMapper<DT, D> {
    fun mapToDomain(data: DT): D
    fun mapToData(domain: D): DT
}