package com.wakwak.keyboardshowhidesample.extension

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isVisible() = visibility == View.VISIBLE
