package com.example.mytestproject.util.BankDetailsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Shamivul.r on 22-12-2022.
 */

@Dao
interface BankDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(bank: Bank)

}