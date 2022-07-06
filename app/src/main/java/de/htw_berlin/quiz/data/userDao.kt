package de.htw_berlin.quiz.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Data Access Objects (DAO) are the main classes where you define your database interactions.
 * They can include a variety of query methods.
 */

@Dao
interface userDao {

        /**
         * add new user
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(user: User)

        /**
         * get all users
         */
        @Query("SELECT * FROM user_table ORDER BY punkte DESC")
        fun getAllUsers(): List<User>

        /**
         * find users with same points
         */
        @Query("SELECT * from user_table WHERE punkte = :punkte")
        fun getUserByPoints(punkte : Int): List<User>


        /**
         * delete users
         */
        @Query("DELETE FROM user_table WHERE id = :userId")
        fun deleteUserById(userId: Int)


        /**
         * delete all users
         */
        @Query("DELETE FROM user_table")
        fun delete()



}