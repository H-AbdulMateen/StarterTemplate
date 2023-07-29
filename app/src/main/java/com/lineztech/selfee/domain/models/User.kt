package com.lineztech.selfee.domain.models

data class User(
    val userId: String,
    val name: String,
    val email: String,
    val mobileNumber: String,
    val address: String,
    val postalCode: String,
    val city: String,
    val imageData: String
)
