package com.example.mytestproject.util.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytestproject.util.BankDetailsDao.Bank
import com.example.mytestproject.util.BankDetailsDao.BankDao

/**
 * Created by Shamivul.r on 22-12-2022.
 */

@Database(entities = [Bank::class], version = 2)
abstract class MyRoomDataBase: RoomDatabase(){
    abstract fun bankDao(): BankDao

    companion object{
        private var myInstance: MyRoomDataBase? = null
        fun getInstance(context: Context):  MyRoomDataBase{
            return myInstance ?: synchronized(this){
                val instance  = Room.databaseBuilder(context.applicationContext, MyRoomDataBase::class.java, "my_db").build()
                myInstance = instance
                return instance
            }
        }
    }
}