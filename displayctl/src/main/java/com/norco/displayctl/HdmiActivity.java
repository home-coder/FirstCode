package com.norco.displayctl;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HdmiActivity extends ActionBarActivity {
    private String[] DisplayModeStr = {
            "1920x1080",
            "1024x768",
            "1280x960",
            "800x600",
            "1920x1080",
            "1024x768",
            "1280x960",
            "1920x1080",
            "1024x768",
            "1280x960",
            "1920x1080",
            "1024 x 768",
            "1280 x 960",
            "1920 x 1080",
            "1024 x 768",
            "1280 x 960",
            "1920 x 1080",
            "1024 x 768",
            "1280 x 960",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdmi);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HdmiActivity.this, android.R.layout.simple_list_item_1, DisplayModeStr);
        ListView listView = (ListView)findViewById(R.id.list_view_hdmi);
        listView.setAdapter(adapter);
    }

}
