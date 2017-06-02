package com.norco.mycalculate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends ActionBarActivity {
    static final String TAG = "MyCalculate";
    TextView textView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e(TAG, "========================");
        textView = (TextView)findViewById(R.id.result_view);
        /*
        * get intent
        * get String
        * parse int
        * calulate
        * set textview*/
        Intent intent = getIntent();
        String oneStr = intent.getStringExtra("pone");
        String secStr = intent.getStringExtra("ptwo");

        int oneInt = Integer.parseInt(oneStr);
        int secInt = Integer.parseInt(secStr);

        int result = oneInt * secInt;

        textView.setText(result + "");
    }

}
