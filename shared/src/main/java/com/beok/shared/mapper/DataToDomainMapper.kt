package com.beok.shared.mapper

interface DataToDomainMapper<T> {
    fun toDomain(): T
}
