package com.kk.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.kk.dialog.R;
import java.io.File;
import java.util.List;

class FileAdapter extends ArrayAdapter<File> {

    protected boolean isHighHint = false;

    protected Drawable folderIcon;
    protected Drawable fileIcon;
    protected int selectColor = 0xff0099cc;
    protected int normalColor = 0x00000000;
    protected File selectFile;
    protected FileSelectListener mListener;

    @SuppressWarnings("deprecation")
    public FileAdapter(Context context, List<File> files) {
        super(context, android.R.layout.simple_list_item_1, files);
        if (Build.VERSION.SDK_INT >= 21) {
            folderIcon = context.getDrawable(R.drawable.ic_menu_archive);
        } else {
            folderIcon = context.getResources().getDrawable(R.drawable.ic_menu_archive);
        }
        fileIcon = null;
    }

    public void setListener(FileSelectListener listener) {
        mListener = listener;
    }

    public void setHighHint(boolean isHighHint) {
        this.isHighHint = isHighHint;
    }

    protected boolean canSelect(File file) {
        return file != null && file.isFile();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView view = null;
        if (convertView == null) {
            convertView = super.getView(position, convertView, parent);
        }
        view = (TextView) convertView;
        final File file = getItem(position);
        if (position != 0)
            view.setText(" " + file.getName());
        else
            view.setText(R.string.dialog_goback);
        if (file.isDirectory()) {
            setDrawable(view, folderIcon);
            if (isHighHint) {
                view.setBackgroundColor(normalColor);
            }
        } else {
            //new SetDrawableTask(this, view).execute(file);
            setDrawable(view, fileIcon);
            if (isHighHint) {
                if (TextUtils.equals(selectFile.getAbsolutePath(), file.getAbsolutePath())) {
                    view.setBackgroundColor(selectColor);
                } else {
                    view.setBackgroundColor(normalColor);
                }
            }
        }
        if (!isHighHint) {
            view.setBackgroundColor(normalColor);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    if (mListener != null) {
                        mListener.onGoBack(file);
                    }
                } else if (canSelect(file)) {
                    selectFile = file;
                    if (mListener != null) {
                        mListener.OnSelectedFile(file);
                    }
                }
            }
        });
        return view;
    }

    protected void setDrawable(TextView view, Drawable drawable) {
        setDrawable(view, drawable, 60);
    }

    public void setFolderIcon(Drawable folderIcon) {
        this.folderIcon = folderIcon;
    }

    public void setFileIcon(Drawable fileIcon) {
        this.fileIcon = fileIcon;
    }

    protected void setDrawable(TextView view, Drawable drawable, int w) {
        if (view != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, 60, w);
                view.setCompoundDrawables(drawable, null, null, null);
            } else {
                view.setCompoundDrawables(null, null, null, null);
            }
        }
    }
}
