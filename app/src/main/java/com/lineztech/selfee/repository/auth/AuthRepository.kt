package com.lineztech.selfee.repository.auth

import com.lineztech.selfee.domain.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(): Flow<DataState<Boolean>>
}