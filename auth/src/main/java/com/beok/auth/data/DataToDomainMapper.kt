package com.beok.auth.data

interface DataToDomainMapper<T> {
    fun toDomain(): T
}
