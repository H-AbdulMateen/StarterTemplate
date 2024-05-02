package com.abdulmateen.startertemplate.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdulmateen.startertemplate.domain.models.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val primaryKeyId: String,
    val userId: String,
    val name: String,
    val email: String,
    val mobileNumber: String,
    val address: String,
    val postalCode: String,
    val city: String,
    val imageData: String
)
//
//fun UserEntity.mapToDomainModel(): User = User(
//    userId = userId,
//    name = name,
//    email = email,
//    mobileNumber = mobileNumber,
//    address = address,
//    postalCode = postalCode,
//    city = city,
//    imageData = imageData
//)
