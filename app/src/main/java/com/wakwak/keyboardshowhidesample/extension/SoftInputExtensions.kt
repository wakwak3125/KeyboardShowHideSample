package com.wakwak.keyboardshowhidesample.extension

import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

fun AppCompatActivity.toAdjustNothing() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
}

fun AppCompatActivity.toAdjustResize() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}
