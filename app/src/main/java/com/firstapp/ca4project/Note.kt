package com.firstapp.ca4project

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Changed to Long for auto-generated primary key
    val heading: String,
    val content: String
) : Parcelable















//package com.firstapp.ca4project
//
//
//import android.os.Parcelable
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import kotlinx.parcelize.Parcelize
//
//
//@Parcelize
//@Entity(tableName = "notes")
//data class Note (
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val heading: String,
//    val content: String
//) : Parcelable
