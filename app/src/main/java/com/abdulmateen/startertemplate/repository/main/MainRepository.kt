package com.abdulmateen.startertemplate.repository.main

import com.abdulmateen.startertemplate.domain.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun login(): Flow<DataState<Boolean>>
}