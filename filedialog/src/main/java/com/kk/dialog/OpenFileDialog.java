package com.kk.dialog;

import android.content.Context;

/**
 * Created with IntelliJ IDEA. User: Scogun Date: 27.11.13 Time: 10:47
 */
public class OpenFileDialog extends BaseFileDialog {

    public OpenFileDialog(Context context) {
        super(context);
        setHighHint(true);
    }

    public OpenFileDialog(Context context, int themeResId) {
        super(context, themeResId);
        setHighHint(true);
    }
}
