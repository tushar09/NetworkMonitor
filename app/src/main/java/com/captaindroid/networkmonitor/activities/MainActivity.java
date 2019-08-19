package com.captaindroid.networkmonitor.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.captaindroid.networkmonitor.NetworkMonitor;
import com.captaindroid.networkmonitor.utils.OnTrafficUpdateListener;

public class MainActivity extends AppCompatActivity implements OnTrafficUpdateListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(com.captaindroid.networkmonitor.R.layout.activity_main);
        NetworkMonitor.start(1500);
        NetworkMonitor.setOnTrafficUpdateListener(this);
    }

    @Override
    public void onTrafficUpdate(int upload, int download){
        Log.e("asdf", upload + " " + download + " asf");
    }
}
