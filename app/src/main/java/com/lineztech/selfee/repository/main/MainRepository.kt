package com.lineztech.selfee.repository.main

import com.lineztech.selfee.domain.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun login(): Flow<DataState<Boolean>>
}