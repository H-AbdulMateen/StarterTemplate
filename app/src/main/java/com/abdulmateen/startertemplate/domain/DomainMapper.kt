package com.abdulmateen.startertemplate.domain


interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model: T):DomainModel
}