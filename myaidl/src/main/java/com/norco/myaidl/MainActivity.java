package com.norco.myaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/
    IMyAidlInterface mIMyService;
    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder service) {
            // 绑定成功,得到远程服务端的对象，目标完成！！！
            mIMyService = IMyAidlInterface.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName name) {
            // 解除绑定
            mIMyService = null;
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 绑定远程服务端服务
        Intent serviceIntent = new Intent("IMyAidlInterface");
        bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
    }
}
