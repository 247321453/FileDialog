package com.kk.dialog;

import java.io.File;
import java.util.Comparator;

/**
 * Created by Administrator on 2016/1/29.
 */
class FileComparator implements Comparator<File> {
    @Override
    public int compare(File file, File file2) {
        if (file.isDirectory() && file2.isFile())
            return -1;
        else if (file.isFile() && file2.isDirectory())
            return 1;
        else
            return file.getPath().compareTo(file2.getPath());
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof File){
            return true;
        }
        return false;
    }
}
