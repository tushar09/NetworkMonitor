package com.captaindroid.networkmonitor.activities;

import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.captaindroid.networkmonitor.NetworkMonitor;
import com.captaindroid.networkmonitor.utils.OnTrafficUpdateListener;

public class MainActivity extends AppCompatActivity implements OnTrafficUpdateListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(com.captaindroid.networkmonitor.R.layout.activity_main);
////        NetworkMonitor.setOnTrafficUpdateListener(new OnTrafficUpdateListener(){
////            @Override
////            public void setOnTrafficUpdate(int upload, int download){
////                Log.e("asdf", upload + " " + download);
////            }
////        });
//        NetworkMonitor.start(1000);
//        NetworkMonitor.start(1000);
//        NetworkMonitor.start(1000);
        NetworkMonitor.start(1000);
        NetworkMonitor.setOnTrafficUpdateListener(this);
    }

    @Override
    public void setOnTrafficUpdate(int upload, int download){
        Log.e("asdf", upload + " " + download);
    }
}
