package com.norco.myspi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.ServiceManager;
import android.os.ISpiService;

    /*
    *    1.获取服务
    *    2.发送文本
    *    3.接收文本
    *
    * 文本输入，文本显示，按键发送和读取。this is a demo !
    *
    * 文本 发送字符 字符串 的本质是发送assic码，数字的可以直接byte[]发送。
    * */


public class MainActivity extends Activity {
    public static final String TAG = "MySpi";

    private ISpiService spiService = null;
    private EditText editText      = null;
    private EditText showText      = null;
    private static int globalReadLen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spiService = ISpiService.Stub.asInterface(ServiceManager.getService("spiservice"));
        editText = (EditText) findViewById(R.id.edit_text);
        showText = (EditText) findViewById(R.id.show_text);
        //TODO 发送 接收 清除
        setSendFunc(R.id.button_1);
        setDumpFunc(R.id.button_2);
        setClearFunc(R.id.button_3);
    }

    public void innerRead(int lenth) {
        try {
            byte[] readbyte = new byte[lenth];
            for (int i = 0; i < readbyte.length; i++) {
                readbyte[i] = 0x00;
            }
            spiService.Read(readbyte, readbyte.length);

                        /*test read*/
            for (int i = 0; i < readbyte.length; i++) {
                Log.e(TAG, "inner read data byte " + Integer.toHexString(readbyte[i] & 0xff));
            }
            String outputText = new String(readbyte);
            showText.setText(outputText);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException read");
        }
    }

    public void setSendFunc (int id) {
        Button button1 = (Button)findViewById(id);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "send somedata");
                String inputText = editText.getText().toString();
                Log.e(TAG, "send data is " + inputText);

                byte[] writebyte = inputText.getBytes();

                for (int i = 0; i < writebyte.length; i++) {
                    Log.e(TAG, "write data byte " + Integer.toHexString(writebyte[i] & 0xff));
                }

//                byte[] writebyte = {0x1, 0x2, 0x3, 0x4, 0x7, 0x8};

                //give it to a global value;
                globalReadLen = writebyte.length;
                try {
                    spiService.Write(writebyte, writebyte.length);
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException write");
                }
                for (int i = 0; i < writebyte.length; i++) {
                    Log.e(TAG, "write data byte " + Integer.toHexString(writebyte[i] & 0xff));
                }
                /*test read after write*/
                innerRead(writebyte.length);
            }
        });
    }

    public class MyThread implements Runnable{
        public void run() {
            while (true) {
                try {
                    Thread.sleep(80000);
                    try {
                        byte[] readbyte = new byte[globalReadLen];
                        spiService.Read(readbyte, readbyte.length);

                        /*test read*/
                        for (int i = 0; i < readbyte.length; i++) {
                            Log.e(TAG, "read data byte " + readbyte[i]);
                        }

                        String outputText = String.valueOf(readbyte);
                        showText.setText(outputText);
                    } catch (RemoteException e) {
                        Log.e(TAG, "RemoteException read");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read(byte[] readarry, int len) {
        readarry[0] = 3;
        readarry[1] = 7;
    }

    public void setDumpFunc(int id) {
        Button button2 = (Button)findViewById(id);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*make a test java readfunction-->*/
                byte[] readarry = new byte[5];
                readarry[0] = 0;
                readarry[1] = 1;
                Read(readarry, 5);
                Log.e(TAG, "read " + readarry[1]);
                /*<--make a test java readfunction*/

                Log.e(TAG, "read somedata");
                new Thread(new MyThread()).start();
            }
        });
    }

    public void setClearFunc (int id) {
        Button button3 = (Button)findViewById(id);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String cleartext = "";
                editText.setText(cleartext);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
