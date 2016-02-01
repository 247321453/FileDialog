package com.kk.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
class BaseFileDialog extends AlertDialog implements FileSelectListener {
    protected TextView title;
    protected Context context;
    protected final DisplayMetrics metrics;
    protected String mCurPath;
    protected FileAdapter mFileAdapter;
    protected FileComparator mFileComparator;
    protected boolean isIniting = true;

    protected DialogFileFilter mDialogFileFilter;

    public BaseFileDialog(Context context) {
        this(context, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
    }

    public BaseFileDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        mFileComparator = new FileComparator();
        metrics = context.getResources().getDisplayMetrics();
        mCurPath = Environment.getExternalStorageDirectory().getPath();
        initView();
        isIniting = false;
    }

    public BaseFileDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        this(context);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    protected void initView() {
        title = createTitle();
        updateTitle();
        getFileAdapter();
        LinearLayout linearLayout = createMainLayout(context);
        ListView listView = new ListView(context);
        linearLayout.addView(listView);
        listView.setAdapter(mFileAdapter);
        setCustomTitle(title);
        setView(linearLayout);
    }

    public FileAdapter getFileAdapter() {
        if (mFileAdapter == null) {
            mFileAdapter = new FileAdapter(context, new ArrayList<File>());
        }
        return mFileAdapter;
    }

    public void setDialogFileFilter(DialogFileFilter dialogFileFilter) {
        mDialogFileFilter = dialogFileFilter;
    }

    public File getSelectFile() {
        return mFileAdapter.selectFile;
    }

    protected TextView createTitle() {
        TextView textView = createTextView(android.R.style.TextAppearance_DeviceDefault_DialogWindowTitle);
        return textView;
    }

    @SuppressWarnings("deprecation")
    protected TextView createTextView(int style) {
        TextView textView = new TextView(context);
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(style);
        } else {
            textView.setTextAppearance(context, style);
        }
        int itemHeight = getItemHeight();
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, itemHeight));
        textView.setMinHeight(itemHeight);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding((int) dp2px(8), 0, 0, 0);
        return textView;
    }

    @Override
    public void onGoBack(File curDir) {
        mCurPath = curDir.getParentFile().getAbsolutePath();
        updateFile(mCurPath);
    }

    public void setHighHint(boolean isHighHint) {
        mFileAdapter.setHighHint(isHighHint);
    }

    public void setFolderIcon(Drawable folderIcon) {
        mFileAdapter.setFolderIcon(folderIcon);
    }

    public void setFileIcon(Drawable fileIcon) {
        mFileAdapter.setFileIcon(fileIcon);
    }

    @Override
    public void OnSelectedFile(File file) {
        mFileAdapter.notifyDataSetChanged();
    }

    protected void updateFile(String curPath) {
        updateTitle();
        //刷新
        mFileAdapter.clear();
        mFileAdapter.addAll(getFiles(curPath));
        mFileAdapter.notifyDataSetChanged();
    }

    protected float dp2px(float dp) {
        return (dp / 2.0f) * metrics.density;
    }

    protected int getItemHeight() {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(
                android.R.attr.listPreferredItemHeightSmall, value, true);
        return (int) TypedValue.complexToDimension(value.data, metrics);
    }

    public void setCurPath(String curPath) {
        mCurPath = curPath;
        if (!isIniting) {
            updateFile(curPath);
        }
    }

    public String getCurPath() {
        return mCurPath;
    }

    public void updateTitle() {
        String titleText = mCurPath;
        int screenWidth = metrics.widthPixels;
        int maxWidth = (int) (screenWidth * 0.8f);
        if (getTextWidth(titleText, title.getPaint()) > maxWidth) {
            while (getTextWidth("..." + titleText, title.getPaint()) > maxWidth) {
                int start = titleText.indexOf("/", 2);
                if (start > 0)
                    titleText = titleText.substring(start);
                else
                    titleText = titleText.substring(2);
            }
            title.setText("..." + titleText);
        } else {
            title.setText(titleText);
        }
    }

    protected LinearLayout createMainLayout(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setMinimumHeight(Math.round((float) metrics.heightPixels * 0.75f));
        return linearLayout;
    }

    protected int getTextWidth(String text, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.left + bounds.width() + (int) dp2px(40);
    }

    protected List<File> getFiles(String directoryPath) {
        File directory = new File(directoryPath);
        List<File> fileList = new ArrayList<File>();
        if (directory.getParentFile() != null)
            fileList.add(directory.getParentFile());
        if (mDialogFileFilter == null) {
            mDialogFileFilter = new DialogFileFilter(null, false);
        }
        fileList.addAll(Arrays.asList(directory.listFiles(mDialogFileFilter)));
        Collections.sort(fileList, mFileComparator);
        return fileList;
    }
}
