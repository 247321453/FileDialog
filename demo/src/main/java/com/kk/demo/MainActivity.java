package com.kk.demo;

import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.kk.dialog.FileDialog;

import java.io.File;

public class MainActivity extends AppCompatActivity implements FileDialog.FileChooseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        Button button1 = new Button(this);
        button1.setText("open");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileDialog fileDialog = new FileDialog(MainActivity.this, FileDialog.Mode.OpenFile);
                fileDialog.setTitle("打开文件");
                fileDialog.setPath(Environment.getExternalStorageDirectory(), null);
                fileDialog.setFileChooseListener(MainActivity.this);
                fileDialog.show();
            }
        });
        linearLayout.addView(button1);

        Button button2 = new Button(this);
        button2.setText("save");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileDialog fileDialog = new FileDialog(MainActivity.this, FileDialog.Mode.SaveFile);
                fileDialog.setTitle("保存文件");
                fileDialog.setPath(Environment.getExternalStorageDirectory(), null);
                fileDialog.setFileChooseListener(MainActivity.this);
                fileDialog.show();
            }
        });
        linearLayout.addView(button2);
        Button button3 = new Button(this);
        button3.setText("choose dir");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileDialog fileDialog = new FileDialog(MainActivity.this, FileDialog.Mode.ChooseDirectory);
                fileDialog.setTitle("选择文件夹");
                fileDialog.setPath(Environment.getExternalStorageDirectory(), null);
                fileDialog.setFileChooseListener(MainActivity.this);
                fileDialog.show();
            }
        });
        linearLayout.addView(button3);
    }

    @Override
    public void onChoose(DialogInterface dialog, File file) {
		if(file == null){
			Toast.makeText(this, "choose nothing", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "choose " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
		}
        
    }
}
