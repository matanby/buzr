package com.example.iraltman.buzr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BootReceiver", "Starting the WifiScanService service");

        Intent myIntent = new Intent(context, WifiScanService.class);
        context.startService(myIntent);

    }
}