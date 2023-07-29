package com.lineztech.selfee.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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
