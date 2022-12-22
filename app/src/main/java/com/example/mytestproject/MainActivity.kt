package com.example.mytestproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mytestproject.databinding.ActivityMainBinding
import com.example.mytestproject.util.BankDetailsDao.Bank
import com.example.mytestproject.util.afterTextChanged
import com.example.mytestproject.util.viewmodel.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isPanValid = false
    private var dob = ""
    private var dateformat = "dd-MM-yyyy"
    private var myViewModel: MyViewModel? = null

    private val panPattern: String = "[A-Z]{5}[0-9]{4}[A-Z]{1}"
    private var day = ""
    private var month = ""
    private var year = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[MyViewModel::class.java]

        binding.panNumberEditText.afterTextChanged {
            if(it.isNotEmpty()) {
                val pattern = Pattern.compile(panPattern)
                val matcher = pattern.matcher(it)
                if(matcher.matches()){
                    isPanValid = true
                }
                checkValidation()
            }
        }

        binding.dayEt.afterTextChanged {
            day = it
            checkValidation()
        }

        binding.monthEt.afterTextChanged {
            month = it
            checkValidation()
        }

        binding.yearEt.afterTextChanged {
            year = it
            checkValidation()
        }

        binding.nextBtn.setOnClickListener {
            myViewModel!!.saveData(Bank(0, binding.panNumberEditText.text.toString(), dob))

        }

        binding.noPanTv.setOnClickListener {
            finish()
        }

        myViewModel!!.isSaved.observe(this) {
            if (it) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkValidation() {
        if (day.isNotEmpty() && month.isNotEmpty() && year.isNotEmpty() && isPanValid){
            val stringBuilder = StringBuilder()
            stringBuilder.append(day).append("-")
                .append(month).append("-")
                .append(year).append(year)
            try {
                val df = SimpleDateFormat(dateformat, Locale.US)
                df.apply {
                    isLenient = false
                    parse(stringBuilder.toString())
                }
                binding.nextBtn.isEnabled = true
                dob = stringBuilder.toString()
            }catch (e: ParseException){
                e.printStackTrace()
                binding.nextBtn.isEnabled = false
                Toast.makeText(this,"Please Enter Valid Date", Toast.LENGTH_SHORT).show()
            }
        }
    }
}