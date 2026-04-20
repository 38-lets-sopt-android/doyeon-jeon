package com.example.letssopt.core.common.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(resId), duration).show()
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
