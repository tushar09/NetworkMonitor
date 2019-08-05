package com.captaindroid.networkmonitor.activities;

import android.net.Uri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.captaindroid.networkmonitor.NetworkMonitor;
import com.captaindroid.networkmonitor.R;
import com.captaindroid.networkmonitor.utils.OnTrafficUpdateListener;

public class MainActivity extends AppCompatActivity implements OnTrafficUpdateListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(com.captaindroid.networkmonitor.R.layout.activity_main);
        NetworkMonitor.start(1000);
        NetworkMonitor.setOnTrafficUpdateListener(this);
        //View view = getLayoutInflater().inflate(R.layout.edit_text, null);
        //ActionBar mActionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.edit_text);
    }

    @Override
    public void setOnTrafficUpdate(int upload, int download){
        Log.e("asdf", upload + " " + download);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
