package com.norco.mycalculate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private EditText editTextOne = null;
    private TextView textView    = null;
    private EditText editTextSec = null;
    private Button buttonCal     = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextOne = (EditText)findViewById(R.id.left_one);
        textView    = (TextView)findViewById(R.id.text);
        editTextSec = (EditText)findViewById(R.id.left_second);
        buttonCal = (Button)findViewById(R.id.button_cal);

        textView.setText(R.string.text_view);
        buttonCal.setText(R.string.button_view);
        setButtonCal(buttonCal);
    }

    public void setButtonCal(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String one = editTextOne.getText().toString();
                String two = editTextSec.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("pone", one);
                intent.putExtra("ptwo", two);
                intent.setClass(MainActivity.this, Main2Activity.class);
                MainActivity.this.startActivity(intent);
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
