package com.abdulmateen.startertemplate.feature.auth.repository

import com.abdulmateen.startertemplate.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(): Flow<DataState<Boolean>>
}