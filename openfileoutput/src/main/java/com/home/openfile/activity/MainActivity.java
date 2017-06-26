package com.home.openfile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.home.openfile.helper.FileHelper;
import com.home.openfileoutput.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        FileHelper fileHelper = new FileHelper();
        String data = "1234567890qwertyujik";
        fileHelper.save(MainActivity.this, data);
    }
}
