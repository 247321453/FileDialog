package com.kk.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.kk.ui.dialog.R;

import java.io.File;

public class SaveFileDialog extends BaseFileDialog {
    private EditText mEditText;
    private Button mNewBtn;

    public SaveFileDialog(Context context) {
        super(context);
        setHighHint(false);
    }

    @Override
    protected LinearLayout createMainLayout(Context context) {
        LinearLayout linearLayout = super.createMainLayout(context);
        LinearLayout tmplayout = new LinearLayout(context);
        tmplayout.setOrientation(LinearLayout.HORIZONTAL);
        mEditText = new EditText(context);
        mEditText.setLayoutParams(new LayoutParams(0,
                LayoutParams.MATCH_PARENT, 1));
        mEditText.setGravity(Gravity.CENTER_VERTICAL);
        mNewBtn = addDirButton(context);
        tmplayout.addView(mEditText);
        tmplayout.addView(mNewBtn);
        linearLayout.addView(tmplayout);
        return linearLayout;
    }

    protected void createDir(File file) {
        if(!file.exists()){
            file.mkdirs();
        }
        else if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @SuppressWarnings("deprecation")
    private Button addDirButton(Context context) {
        Button btn = new Button(context);
        if (Build.VERSION.SDK_INT >= 23) {
            btn.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Small);
        } else {
            btn.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Small);
        }
        btn.setText(R.string.create_folder);
        btn.setGravity(Gravity.CENTER);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(mCurPath, mEditText.getText()
                        .toString());
                createDir(file);
                updateFile(file.getAbsolutePath());
            }
        });
        return btn;
    }

    public SaveFileDialog setEditText(String str) {
        if (mEditText != null)
            mEditText.setText(str);
        return this;
    }
}
