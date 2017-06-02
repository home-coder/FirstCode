package com.carmak.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by java on 16-4-7.
 */
public class HelperFirst extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_layout);
        openFan();
        closeFan();
    }

    public void openFan () {
        Button button8=(Button)findViewById(R.id.button_open);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelperFirst.this, "i have touched open button", Toast.LENGTH_SHORT).show();
                /*打开风扇*/
//                finish();
            }
        });
    }

    public void closeFan () {
        Button button9 = (Button)findViewById(R.id.button_close);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelperFirst.this, "i have touched close button", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.helper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.helper_item:
                Toast.makeText(this, "show something by item_see", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
