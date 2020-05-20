package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class AddActivity extends AppCompatActivity {

    private Button add_btn1;
    private EditText save_et1;

    String filePath = Environment.getExternalStorageDirectory()+ "/save/";
    String fileName = "save.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView(){
        save_et1 = findViewById(R.id.sava_et1);
        add_btn1 = findViewById(R.id.add_btn1);
        add_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeTextToFile(save_et1.getText().toString().trim(),filePath,fileName);
                finish();
            }
        });
    }



    public void writeTextToFile(String strcontent,String filePath,String fileName){
        makeFilePath(filePath,fileName);
        String strFilePath = filePath + fileName;
        String strContent = strcontent + "\r\n";
        try{
            File file = new File(strFilePath);
            if(!file.exists()){
                Log.d("TestFile","Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file,"rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public File makeFilePath(String filePath,String fileName){
        File file = null;
        makeRootDirectory(filePath);
        try{
            file = new File(filePath+fileName);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }
    public static void makeRootDirectory(String filePath){
        File file = null;
        try{
            file = new File(filePath);
            if(!file.exists()) {
                file.mkdir();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
