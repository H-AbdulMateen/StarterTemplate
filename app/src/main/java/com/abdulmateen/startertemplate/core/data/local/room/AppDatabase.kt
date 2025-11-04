package com.abdulmateen.startertemplate.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdulmateen.startertemplate.core.data.local.room.dao.UserDao
import com.abdulmateen.startertemplate.core.data.local.room.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        const val DATABASE_NAME = "my_gen_room_database"
    }
}