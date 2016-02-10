package com.kk.dialog;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

public class DialogFileFilter implements FilenameFilter {
    protected String[] filters;
    protected boolean isAll;
    protected boolean onlyDir;

    /***
     * @param filters 后缀格式
     * @param onlyDir 仅文件夹
     */
    public DialogFileFilter(String filters, boolean onlyDir) {
        this.isAll = filters == null || filters.trim().length() == 0 || filters.equals("*") || filters.equals("*.*");
        if (!this.isAll) {
            this.filters = filters.toLowerCase(Locale.US).split("|");
        }
        this.onlyDir = onlyDir;
    }

    @Override
    public boolean accept(File dir, String filename) {
        if (filename == null) return false;
        File file = new File(dir, filename);
        if (onlyDir) {
            return file.isDirectory();
        }
        if (isAll) return true;
        filename = filename.toLowerCase(Locale.US);
        for (String f : filters) {
            if (filename.endsWith(f)) {
                return true;
            }
        }
        return false;
    }
}
