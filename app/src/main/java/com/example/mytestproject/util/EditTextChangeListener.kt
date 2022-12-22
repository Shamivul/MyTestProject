package com.example.mytestproject.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Shamivul.r on 22-12-2022.
 */

fun EditText.afterTextChanged(afterTextChanged: (String)-> Unit){
    this.addTextChangedListener(object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0.toString())
        }

    })
}