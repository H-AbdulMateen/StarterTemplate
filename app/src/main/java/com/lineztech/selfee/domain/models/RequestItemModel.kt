package com.lineztech.selfee.domain.models

data class RequestItemModel(
    val imageUrl: String,
    val title: String,
    val distance: String,
    val description: String,
    val price: String
)
