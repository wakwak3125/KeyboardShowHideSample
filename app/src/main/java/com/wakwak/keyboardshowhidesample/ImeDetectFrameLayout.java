package com.wakwak.keyboardshowhidesample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import static java.security.AccessController.getContext;

/**
 * Created by Ryo on 2016/10/18.
 */

public class ImeDetectFrameLayout extends FrameLayout {
    private static final String TAG = ImeDetectFrameLayout.class.getSimpleName();

    public ImeDetectFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int measuredHeight = getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.v(TAG, "ImeDetectFrameLayout " +
                "measuredHeight: " + measuredHeight + " getMeasuredHeight(): " +
                getMeasuredHeight());
        if (measuredHeight != getMeasuredHeight() && getContext() instanceof ImeUtil.ImeStateHost) {
            ((ImeUtil.ImeStateHost) getContext()).onDisplayHeightChanged(heightMeasureSpec);
        }
    }
}
