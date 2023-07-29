package com.abdulmateen.startertemplate.data.cache.dao
import androidx.room.*
import com.abdulmateen.startertemplate.data.cache.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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