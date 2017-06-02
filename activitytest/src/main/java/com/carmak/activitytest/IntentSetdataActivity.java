package com.carmak.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by java on 16-4-12.
 */
public class IntentSetdataActivity extends Activity {
    public static final String TAG = "IntentSetdataActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentsetdata_layout);
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("Why not");

        String getData;
        Intent intent = getIntent();
        getData = intent.getStringExtra("set_data");
        Log.e(TAG, getData);
        //显示不出设置的文字==============>
        tv.setText(getData);
        Log.e(TAG, "过程结束");
    }
}
