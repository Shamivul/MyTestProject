package com.example.mytestproject.util.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestproject.util.BankDetailsDao.Bank
import com.example.mytestproject.util.room.MyRoomDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Shamivul.r on 22-12-2022.
 */
class MyViewModel(application: Application) : AndroidViewModel(application) {
    private var appDb: MyRoomDataBase = MyRoomDataBase.getInstance(application)
    var isSaved: MutableLiveData<Boolean> = MutableLiveData()

    fun saveData(bank: Bank){
        viewModelScope.launch {
            appDb.bankDao().insertDetails(bank)
            isSaved.postValue(true)
        }
    }
}