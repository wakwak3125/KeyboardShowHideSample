package com.wakwak.keyboardshowhidesample

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Ryo on 2016/10/18.
 */

object ImeUtil {

    fun hideImeKeyboard(context: Context, v: View) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0 /* flags */)
    }

    fun showImeKeyboard(context: Context, v: View) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        v.requestFocus()
        inputMethodManager.showSoftInput(v, 0 /* flags */)
    }

    fun hideSoftInput(context: Context, v: View) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
