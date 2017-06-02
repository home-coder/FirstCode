package com.carmak.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by java on 16-4-11.
 */
public class IntentCallbackActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentcallback_layout);
        String data = "向上传递一个数据";
        Intent intent = new Intent();
        intent.putExtra("data_return", data);
        setResult(RESULT_OK, intent);
        finish();
    }
}
