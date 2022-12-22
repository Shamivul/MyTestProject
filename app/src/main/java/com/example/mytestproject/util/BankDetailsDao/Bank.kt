package com.example.mytestproject.util.BankDetailsDao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Shamivul.r on 22-12-2022.
 */

@Entity
data class Bank(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "pan") val panNumber: String,
    @ColumnInfo(name = "dob") val dob: String
)
