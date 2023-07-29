package com.lineztech.selfee.domain


interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model: T):DomainModel
}