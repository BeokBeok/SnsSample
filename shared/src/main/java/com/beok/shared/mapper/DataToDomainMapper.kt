package com.beok.shared.mapper

interface DataToDomainMapper<T> {
    fun toDomain(): T
}

fun <T> List<DataToDomainMapper<T>>.toDomain() = map { it.toDomain() }
