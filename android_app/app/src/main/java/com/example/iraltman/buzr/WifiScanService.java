package com.example.iraltman.buzr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WifiScanService extends Service {
    private static final int MIN_RSSI_LEVEL = 8;
    private static final String SSID_PREFIX = "buzr";
    private static long UPDATE_INTERVAL = 5 * 1000;
    private static Timer timer = new Timer();

    WifiManager wifi;
    WifiScanReceiver wifiReciever;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        _startService();

        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    private void _startService() {
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        doServiceWork();
                    }
                }, 1000,UPDATE_INTERVAL);
        Log.i(getClass().getSimpleName(), "FileScannerService Timer started....");

        wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiReciever = new WifiScanReceiver();
        registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private void doServiceWork()
    {
        //do something wotever you want
        //like reading file or getting data from network

        try {
            Log.i(getClass().getSimpleName(), "Wotever");
            wifi.startScan();
        }
        catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.toString());

        }

    }

    private void _shutdownService()
    {
        if (timer != null) timer.cancel();
        Log.i(getClass().getSimpleName(), "Timer stopped...");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        _shutdownService();

        // if (MAIN_ACTIVITY != null)  Log.d(getClass().getSimpleName(), "FileScannerService stopped");
    }

    private class WifiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            Log.i(getClass().getSimpleName(), "WHAZAAAA");

            List<ScanResult> wifiScanList = wifi.getScanResults();
            for (ScanResult scan : wifiScanList) {
                if (WifiManager.calculateSignalLevel(scan.level, 10) >= MIN_RSSI_LEVEL) {
                    if (scan.SSID.startsWith(SSID_PREFIX)) {
                        Log.i(getClass().getSimpleName(), scan.SSID);
                    }
                }

            }

            for(ScanResult sr : wifiScanList) {
                Log.i(getClass().getSimpleName(), sr.toString());
            }


            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(c)
                            .setSmallIcon(R.drawable.ic_menu_camera)
                            .setContentTitle("Buzr")
                            .setContentText("Hello World!");

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // mId allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build());
        }
    }
}
