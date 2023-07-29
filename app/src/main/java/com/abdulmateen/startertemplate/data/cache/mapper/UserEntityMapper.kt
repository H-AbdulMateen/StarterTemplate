package com.abdulmateen.startertemplate.data.cache.mapper

import com.abdulmateen.startertemplate.data.cache.entities.UserEntity
import com.abdulmateen.startertemplate.domain.DomainMapper
import com.abdulmateen.startertemplate.domain.models.User
import com.abdulmateen.startertemplate.utils.AppConstants.USER_ID_CACHE

class UserEntityMapper: DomainMapper<UserEntity, User> {
    override fun mapToDomainModel(model: UserEntity): User {
        return User(
            userId = model.userId,
            name = model.name,
            email = model.email,
            mobileNumber = model.mobileNumber,
            address = model.address,
            postalCode = model.postalCode,
            city = model.city,
            imageData = model.imageData
        )
    }

    fun mapFromDomainModel(domainModel: User): UserEntity{
        return UserEntity(
            primaryKeyId = USER_ID_CACHE,
            userId = domainModel.userId,
            name = domainModel.name,
            email = domainModel.email,
            mobileNumber = domainModel.mobileNumber,
            address = domainModel.address,
            postalCode = domainModel.postalCode,
            city = domainModel.city,
            imageData = domainModel.imageData

        )
    }
}