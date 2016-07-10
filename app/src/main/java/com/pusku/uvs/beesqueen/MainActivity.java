package com.pusku.uvs.beesqueen;


import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity  implements TabHost.OnTabChangeListener {
    /** Called when the activity is first created. */
  static  TabHost tabHost;

    // логгируем переключение вкладок


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ориентация экрана только портретная
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // получаем TabHost
         tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);

        // инициализация была выполнена в getTabHost
        // метод setup вызывать не нужно

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Календарь");
        tabSpec.setContent(new Intent(this, Calendar.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Метки");
        tabSpec.setContent(new Intent(this, Label.class));
        tabHost.addTab(tabSpec);
    }

    @Override
    public void onTabChanged(String tabId) {


      if(tabId.equals("tag1")) Calendar.choice(Count.i);
    }
}