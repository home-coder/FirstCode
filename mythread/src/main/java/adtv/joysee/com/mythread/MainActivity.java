package adtv.joysee.com.mythread;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int CHANGE_TXT = 1;
    public static final int RESET_TXT = 2;

    private TextView textView;
    private Button button;
    private boolean flag = true;

    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CHANGE_TXT:
                    textView.setText("nihao shijie");
                    break;
                case RESET_TXT:
                    //sharepreference
                    SharedPreferences preferences = getSharedPreferences("mydata", MODE_PRIVATE);
                    textView.setText(preferences.getString("txt", ""));
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        button = (Button) findViewById(R.id.bt_change);

        SharedPreferences.Editor editor = getSharedPreferences("mydata", MODE_PRIVATE).edit();
        editor.putString("txt", textView.getText().toString());
        editor.apply();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        if (flag) {
                            msg.what = CHANGE_TXT;
                            flag = false;
                        } else {
                            msg.what = RESET_TXT;
                            flag = true;
                        }
                        mMainHandler.sendEmptyMessage(msg.what);
                   }
                }).start();
            }
        });
    }
}
