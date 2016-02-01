package com.kk.dialog;

import java.io.File;

/**
 * Created by Administrator on 2016/1/13.
 */
interface FileSelectListener {
    void onGoBack(File curDir);

    void OnSelectedFile(File file);
}
