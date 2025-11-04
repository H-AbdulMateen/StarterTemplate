package com.abdulmateen.startertemplate.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abdulmateen.startertemplate.core.data.local.room.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("UPDATE user_table SET name= :updatedName WHERE primaryKeyId= :primaryKey")
    suspend fun updateUserBalance(updatedName: String, primaryKey: String)

    @Query("SELECT * FROM user_table WHERE primaryKeyId= :primaryKey")
    suspend fun getCurrentUser(primaryKey: String): UserEntity

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Delete
    suspend fun deleteUser(user: UserEntity)
}