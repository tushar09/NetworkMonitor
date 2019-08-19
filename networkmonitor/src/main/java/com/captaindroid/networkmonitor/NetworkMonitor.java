package com.captaindroid.networkmonitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.captaindroid.networkmonitor.utils.CommonComponent;
import com.captaindroid.networkmonitor.utils.OnTrafficUpdateListener;

public class NetworkMonitor{

    private static OnTrafficUpdateListener otul;
    private static Handler handler;
    private static Runnable runnable;

    private static long dn, up;


    public static boolean isInternetConnected(Context ctx) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) ctx
                .getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        // Check if wifi or mobile network is available or not. If any of them is
        // available or connected then it will return true, otherwise false;
        if (wifi != null) {
            if (wifi.isConnected()) {
                return true;
            }
        }
        if (mobile != null) {
            if (mobile.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static CommonComponent.NetworkType getConnectedNetworkType(Context ctx) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) ctx
                .getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        // Check if wifi or mobile network is available or not. If any of them is
        // available or connected then it will return true, otherwise false;
        if (wifi != null) {
            if (wifi.isConnected()) {
                return CommonComponent.NetworkType.WIFI;
            }
        }
        if (mobile != null) {
            if (mobile.isConnected()) {
                return CommonComponent.NetworkType.DATA;
            }
        }
        return CommonComponent.NetworkType.NONE;
    }


    public static void setOnTrafficUpdateListener(@NonNull OnTrafficUpdateListener onTrafficUpdateListener){
        otul = onTrafficUpdateListener;
    }

    public static void start(final int networkUpdateFrequencyInMilliSecond){
        if(handler == null){
            handler = new Handler();
        }else {
            handler.removeMessages(1);
            handler.removeCallbacks(runnable);
        }
        handler.sendEmptyMessage(1);
        runnable = new Runnable(){
            public void run(){
                //Log.e("called", "null");
                if(otul != null){
                    //Log.e("called", "null");
                    if(up != 0){
                        otul.onTrafficUpdate((int)(Math.abs(TrafficStats.getTotalTxBytes() - up)), (int)(Math.abs(TrafficStats.getTotalRxBytes() - dn)));
                        up = TrafficStats.getTotalTxBytes();
                        dn = TrafficStats.getTotalRxBytes();
                    }else {
                        up = TrafficStats.getTotalTxBytes();
                        dn = TrafficStats.getTotalRxBytes();
                        otul.onTrafficUpdate(0,0);
                    }

                }
                handler.postDelayed(runnable, networkUpdateFrequencyInMilliSecond);
            }
        };
        handler.postDelayed(runnable, 1);
    }


}
