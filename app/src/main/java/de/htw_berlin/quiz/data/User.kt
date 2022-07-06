package de.htw_berlin.quiz.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entities represent the objects that you want to store. (table)
 */
@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    //@ColumnInfo(name = "u_name")
    var name: String,

    //@ColumnInfo(name = "u_punkte")
    var punkte: Int)









