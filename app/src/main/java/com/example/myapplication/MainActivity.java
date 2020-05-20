package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Button main_save;
    private Button main_watch;
    private TextView main_tv1;
    private Button main_delete;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        main_save = findViewById(R.id.main_save);
        main_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        main_watch = findViewById(R.id.main_watch);
        main_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                main_tv1 = findViewById(R.id.main_tv1);
                showInFo();
            }
        });
        main_delete = findViewById(R.id.main_delete);
        main_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete("save/save.txt");
                main_tv1.setText(null);
            }
        });

        Typeface typeface=Typeface.createFromAsset(getAssets(),"youyuan.ttf");
        main_tv1 = findViewById(R.id.main_tv1);
        main_tv1.setTypeface(typeface);
    }

    public void delete(String fileName){
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        file.delete();
    }

    public void showInFo(){
        File file = new File(Environment.getExternalStorageDirectory(),"save/save.txt");
        String str = null;
        try{
            InputStream is = new FileInputStream(file);
            InputStreamReader input = new InputStreamReader(is,"UTF-8");
            BufferedReader reader = new BufferedReader(input);
            while((str = reader.readLine()) != null){
                main_tv1.append(str);
                main_tv1.append("\n");
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}




