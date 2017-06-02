package com.norco.testproperties;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayArgUtil displayArgUtil = new DisplayArgUtil();
        FileInputStream streamReader = null;   //文件输入流
        try
        {
            // ppts.load(new java.io.FileInputStream(propertyFilePath));
            //new java.io.FileInputStream(propertyFilePath);

            streamReader = new FileInputStream(new File("/home/java/opt/kk/build/Release/boot.ini"));
           // streamReader = new FileInputStream("/home/java/opt/kk/build/Release/boot.ini");

        } catch (IOException e)
        {
            Log.e("=====", "ya.....");
        }
 /*       //TODO
        //   displayArgUtil.setDisplayArg(displayArg);
        //TODO TEST
        String bootpath = displayArgUtil.getBootInitPath();
        System.out.println("boot.init文件地址：" + bootpath);
        System.out.println("**************分隔线***************");
        String bootargs = DisplayArgUtil.getBootargs();
        //  System.out.println(bootargs);
        System.out.println("**************分隔线***************");
        String mxcfb0 = displayArgUtil.getDisplayArg("mxcfb0", bootargs);
        System.out.println(mxcfb0);
        System.out.println("**************分隔线***************");
        mxcfb0 = displayArgUtil.getDisplayArg("mxcfb0");
        System.out.println(mxcfb0);
        System.out.println("**************分隔线***************");
        bootargs = displayArgUtil.getBootargs();
        System.out.println(bootargs);
        displayArgUtil.setDisplayArg("mxcfb0", "video=mxcfb0:dev=lcd,810x480M@120,if=RGB48,bpp=33");
        System.out.println(displayArgUtil.getBootargs());
        System.out.println("**************分隔线***************");
        displayArgUtil.setDisplayArg("video=mxcfb0:dev=lcd11,8122x4833M@123,if=RGB48,bpp=33");
        System.out.println(displayArgUtil.getBootargs());*/
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
