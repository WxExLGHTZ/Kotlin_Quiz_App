package de.htw_berlin.quiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 *  Databaseclass to hold the database.
 *  AppDatabase defines the database configuration and serves as the app's main access point to the persisted data.
 **/
@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class userDatabase : RoomDatabase(){

    abstract fun userDao() : userDao

    companion object {
        private var instance: userDatabase? = null
        @Synchronized
        fun getInstance(ctx: Context): userDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(ctx.applicationContext, userDatabase::class.java, "database").build()
            }
            return instance!!
        }
    }
}