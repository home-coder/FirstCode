package com.carmak.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by java on 16-4-5.
 */

public class FirstActivity extends Activity {
    public static final String TAG = "FirstActivity";
    Button button4;
    static int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*隐藏标题栏*/
    //    requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_layout);
        setOperateFan(R.id.button_1);
        setOperateLed(R.id.button_2);
        setOperateSearch(R.id.button_3);
        setOperateCallback(R.id.button_4);
        setOperateSetdata(R.id.button_5);
        setOperateGpio(R.id.button_6);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    protected void setOperateGpio (int id) {
        Button button6 = (Button)findViewById(id);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       Intent intent = new Intent(FirstActivity.this, GpioActivity.class);
                Intent intent = new Intent(FirstActivity.this, Flexcan.class);
            }
        });
    }

    public void setOperateSetdata (int id) {
        Button button5 = (Button)findViewById(id);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setData = "从FirstActivity页面穿进IntentSetdataActivity的数据";
                Intent intent = new Intent(FirstActivity.this, IntentSetdataActivity.class);
                intent.putExtra("set_data", setData);
                startActivity(intent);
            }
        });
    }

    //跳转页面的setResult方法的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("data_return");
                    Log.e(TAG, "返回的数据给解析出来了");
                    button4.setText(returnData);
                }
                break;
            default:
                break;
        }
    }

    public void setOperateCallback (int id) {
        button4 = (Button)findViewById(id);
        final String oldString = button4.getText().toString();

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (state == 0) {
                    Intent intent = new Intent(FirstActivity.this, IntentCallbackActivity.class);
                    startActivityForResult(intent, 1);
                    state = 1;
                } else {
                    Log.e(TAG, "撤回到最原始的状态");
                    button4.setText(oldString);
                    state = 0;
                }
            }
        });
    }

    public void setOperateSearch (int id) {
        Button button3 = (Button)findViewById(id);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                Log.e(TAG, "www.baidu.com ?");
                startActivity(intent);
            }
        });
    }

    public void setOperateLed(int id) {
        Button button2 = (Button)findViewById(id);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "Operate Led");
                Intent intent = new Intent("com.carmak.activitytest.ACTION_START");
                intent.addCategory("com.carmak.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
        });
    }

    public void setOperateFan(int id) {
        Button button1=(Button)findViewById(id);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "i have touchedc a button");
                Toast.makeText(FirstActivity.this, "i have touched a button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FirstActivity.this, HelperFirst.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "i clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "i clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.move_item:
                Toast.makeText(this, "i clicked Move", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }
}
