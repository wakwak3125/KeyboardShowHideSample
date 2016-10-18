package com.wakwak.keyboardshowhidesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ImeUtil imeUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toAdjustResize();
        imeUtil = ImeUtil.get();
        ImeUtil.set(imeUtil);
        final ImeDetectFrameLayout rootView = (ImeDetectFrameLayout) findViewById(R.id.ime_detact_frame_layout);
        final ImageView attachmentButton = (ImageView) findViewById(R.id.button_plus);
        final View pickerContainer = findViewById(R.id.picker_container);
        final EditText editText = (EditText) findViewById(R.id.editText);
        attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickerContainer.getVisibility() != View.VISIBLE) {
                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    imeUtil.hideImeKeyboard(getApplicationContext(), editText);
                    toAdjustNothing();
                    pickerContainer.setVisibility(View.VISIBLE);
                    getWindow().setAttributes(params);
                } else {
                    pickerContainer.setVisibility(View.GONE);
                    toAdjustResize();
                    imeUtil.showImeKeyboard(getApplicationContext(), editText);
                }
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    toAdjustResize();
                    if (pickerContainer.getVisibility() == View.VISIBLE) {
                        pickerContainer.setVisibility(View.GONE);
                    }
                    imeUtil.showImeKeyboard(getApplicationContext(), editText);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        ImeUtil.clearInstance();
        super.onDestroy();
    }

    private void toAdjustNothing() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    private void toAdjustResize() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}
