package com.wakwak.keyboardshowhidesample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import junit.framework.Assert;

/**
 * Created by Ryo on 2016/10/18.
 */

public class ImeUtil {

    public interface ImeStateObserver {
        void onImeStateChanged(boolean imeOpen);
    }

    public interface ImeStateHost {
        void onDisplayHeightChanged(int heightMeasureSpec);

        void registerImeStateObserver(ImeUtil.ImeStateObserver observer);

        void unregisterImeStateObserver(ImeUtil.ImeStateObserver observer);

        boolean isImeOpen();
    }

    private static volatile ImeUtil sInstance;

    // Used to clear the static cached instance of ImeUtil during testing.  This is necessary
    // because a previous test may have installed a mocked instance (or vice versa).
    public static void clearInstance() {
        sInstance = null;
    }

    public static ImeUtil get() {
        if (sInstance == null) {
            synchronized (ImeUtil.class) {
                if (sInstance == null) {
                    sInstance = new ImeUtil();
                }
            }
        }
        return sInstance;
    }

    @VisibleForTesting
    public static void set(final ImeUtil imeUtil) {
        sInstance = imeUtil;
    }

    public void hideImeKeyboard(@NonNull final Context context, @NonNull final View v) {
        Assert.assertNotNull(context);
        Assert.assertNotNull(v);
        final InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0 /* flags */);
        }
    }

    public void showImeKeyboard(@NonNull final Context context, @NonNull final View v) {
        Assert.assertNotNull(context);
        Assert.assertNotNull(v);
        final InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            v.requestFocus();
            inputMethodManager.showSoftInput(v, 0 /* flags */);
        }
    }

    public static void hideSoftInput(@NonNull final Context context, @NonNull final View v) {
        final InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
