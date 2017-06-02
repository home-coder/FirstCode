package com.norco.keyevent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private IntentFilter intentFilter;
    private VolumeUpReceiver volumeUpReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter.addAction("com.zdzt.VOLUME_LONG_ADD");
        volumeUpReceiver = new VolumeUpReceiver();
        registerReceiver(volumeUpReceiver, intentFilter);
    }

    class VolumeUpReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "volume up long", Toast.LENGTH_SHORT).show();
        }
    }
}
