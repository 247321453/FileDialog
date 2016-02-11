package com.kk.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Hasee on 2016/2/10.
 */
public class MainActivity extends Activity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_open){
            final OpenFileDialog fileDialog = new OpenFileDialog(this);
            fileDialog.setDialogFileFilter(new DialogFileFilter(".msoe-set|.dex-set|.zip", false));
            fileDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    File file = fileDialog.getSelectFile();
                    Toast.makeText(MainActivity.this, ""+file, Toast.LENGTH_SHORT).show();
                }
            });
            fileDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.cancel), (DialogInterface.OnClickListener) null);
            fileDialog.show();
        }else if(item.getItemId()==R.id.menu_save){
            final SaveFileDialog fileDialog = new SaveFileDialog(this);
            fileDialog.setDialogFileFilter(new DialogFileFilter(".msoe-set|.dex-set", false));
            fileDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    File file = fileDialog.getSelectFile();
                    Toast.makeText(MainActivity.this, ""+file, Toast.LENGTH_SHORT).show();
                }
            });
            fileDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.cancel), (DialogInterface.OnClickListener) null);
            fileDialog.show();
        }
        return false;
    }
}
