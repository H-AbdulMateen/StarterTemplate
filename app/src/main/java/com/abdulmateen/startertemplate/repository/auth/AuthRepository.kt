package com.abdulmateen.startertemplate.repository.auth

import com.abdulmateen.startertemplate.domain.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(): Flow<DataState<Boolean>>
}