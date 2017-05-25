package net.kk.dialog;

import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.Locale;

public class FileFilter2 implements FileFilter {
    public static FileFilter2 DEBUFALT = new FileFilter2(false);
    private boolean onlyDir;
    private boolean ignoreCase;
    private boolean showHide;
    private String[] mEx;

    /***
     *
     * @param exs 文件后缀
     */
    public FileFilter2(boolean showHide, String... exs) {
        this.showHide = showHide;
        mEx = exs;
    }

    public void setOnlyDir(boolean onlyDir) {
        this.onlyDir = onlyDir;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public boolean accept(File pathname) {
        if (onlyDir && !pathname.isDirectory()) {
            return false;
        }
        String name = pathname.getName();
        if (!showHide) {
            if (name.startsWith(".")) {
                Log.d("kk", "name=" + name);
                return false;
            }
        }
        if (ignoreCase) {
            name = name.toLowerCase(Locale.US);
        }
        if (mEx != null && mEx.length > 0) {
            for (String ex : mEx) {
                if (name.endsWith(ex)) {
                    return true;
                }
            }
        }else{
            return true;
        }
        return false;
    }
}
