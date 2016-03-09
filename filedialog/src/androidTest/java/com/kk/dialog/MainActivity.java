package com.kk.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Hasee on 2016/2/10.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout=new LinearLayout(this);
        setContentView(linearLayout);
        Button button1=new Button(this);
        button1.setText("open");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OpenFileDialog fileDialog = new OpenFileDialog(MainActivity.this);
                fileDialog.setDialogFileFilter(new DialogFileFilter(false, false));
                fileDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file = fileDialog.getSelectFile();
                        Toast.makeText(MainActivity.this, "" + file, Toast.LENGTH_SHORT).show();
                    }
                });
                fileDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.cancel), (DialogInterface.OnClickListener) null);
                fileDialog.show();
            }
        });
        linearLayout.addView(button1);

        Button button2=new Button(this);
        button2.setText("save");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SaveFileDialog fileDialog = new SaveFileDialog(MainActivity.this);
                fileDialog.setDialogFileFilter(new DialogFileFilter(false));
                fileDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file = fileDialog.getSelectFile();
                        Toast.makeText(MainActivity.this, "" + file, Toast.LENGTH_SHORT).show();
                    }
                });
                fileDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.cancel), (DialogInterface.OnClickListener) null);
                fileDialog.show();
            }
        });
        linearLayout.addView(button2);
        Button button3=new Button(this);
        button3.setText("save 2");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SaveFileDialog fileDialog = new SaveFileDialog(MainActivity.this);
                fileDialog.setDialogFileFilter(new DialogFileFilter(false));
                fileDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file = fileDialog.getSelectFile();
                        Toast.makeText(MainActivity.this, "" + file, Toast.LENGTH_SHORT).show();
                    }
                });
                fileDialog.setHideCreateButton(true);
                fileDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.cancel), (DialogInterface.OnClickListener) null);
                fileDialog.show();
            }
        });
        linearLayout.addView(button3);
    }
}
