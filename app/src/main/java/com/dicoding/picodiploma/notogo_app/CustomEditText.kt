package com.dicoding.picodiploma.notogo_app

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns.EMAIL_ADDRESS
import androidx.appcompat.widget.AppCompatEditText

class CustomEditText : AppCompatEditText {

    var type = ""

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(type == "password") {
                    if(s.length < 6) {
                        error = context.getString(R.string.password_warning)
                    }
                } else if(type == "email") {
                    if(!EMAIL_ADDRESS.matcher(s).matches()) {
                        error = context.getString(R.string.email_warning)
                    }
                } else {
                    if(s.isEmpty()) {
                        error = context.getString(R.string.name_warning)
                    }
                }


            }

            override fun afterTextChanged(s: Editable?) {
                // nothing
            }
        })
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }



}