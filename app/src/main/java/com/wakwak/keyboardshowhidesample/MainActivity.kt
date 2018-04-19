package com.wakwak.keyboardshowhidesample

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import com.wakwak.keyboardshowhidesample.extension.gone
import com.wakwak.keyboardshowhidesample.extension.isVisible
import com.wakwak.keyboardshowhidesample.extension.visible

class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toAdjustResize()

        val attachmentButton = findViewById<ImageView>(R.id.button_plus)
        val pickerContainer = findViewById<RelativeLayout>(R.id.picker_container)
        val editText = findViewById<EditText>(R.id.editText)

        attachmentButton.setOnClickListener {
            if (!pickerContainer.isVisible()) {
                val params = window.attributes
                ImeUtil.hideImeKeyboard(this, editText)
                //toAdjustNothing()
                pickerContainer.visible()
                window.attributes = params
            } else {
                pickerContainer.gone()
                //toAdjustResize()
                ImeUtil.showImeKeyboard(this, editText)
            }
        }

        editText.setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                //toAdjustResize()
                if (pickerContainer.isVisible()) {
                    pickerContainer.gone()
                }
                ImeUtil.showImeKeyboard(this, editText)
                return@OnTouchListener true
            }
            false
        })
    }
}
