package com.example.iraltman.buzr;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WifiScanService extends Service {
    private static final int MIX_PROXIMITY_LEVEL = 8;
    private static final String SSID_PREFIX = "Buzr";
    private static long UPDATE_INTERVAL = 5 * 1000;
    private static Timer timer = new Timer();
    private static API api = new API("http://132.65.251.197:8080");

    WifiManager wifi;
    WifiScanReceiver wifiReceiver;

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
        Log.i(getClass().getSimpleName(), "Received start id " + startId + ": " + intent);
        _startService();

        // We want this service to continue running until it is explicitly stopped, so return sticky.
        return START_STICKY;
    }

    private void _startService() {
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        doServiceWork();
                    }
                }, 1000, UPDATE_INTERVAL);

        Log.i(getClass().getSimpleName(), "Wifi networks scan timer started");
        wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiScanReceiver();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private void doServiceWork()
    {
        try {
            Log.i(getClass().getSimpleName(), "Starting Wifi networks scan...");
            wifi.startScan();
        }
        catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.toString());
        }
    }

    private void _shutdownService()
    {
        if (timer != null) timer.cancel();
        Log.i(getClass().getSimpleName(), "Wifi networks scan timer stopped...");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        _shutdownService();
        // if (MAIN_ACTIVITY != null)
        //     Log.d(getClass().getSimpleName(), "FileScannerService stopped");
    }

    private class WifiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = wifi.getScanResults();
            Log.i(getClass().getSimpleName(), String.format("Received Wifi scan results containing %1$d networks", wifiScanList.size()));

            String locationId = null;
            for (ScanResult scan : wifiScanList) {
                if (WifiManager.calculateSignalLevel(scan.level, 10) >= MIX_PROXIMITY_LEVEL) {
                    if (scan.SSID.startsWith(SSID_PREFIX)) {
                        Log.i(getClass().getSimpleName(), "Found a Buzr network - " + scan.SSID);
                        locationId = scan.SSID.substring(SSID_PREFIX.length()).trim();
                        break;
                    }
                }
            }
            if (locationId != null) {
                List<Deal> deals = api.getNearbyDeals(locationId);
                Log.i(getClass().getSimpleName(), "Fetched information about " + deals.size() + " nearby deals");
                displayNearbyDealsNotification(deals.size(), locationId, c);
            }
            sendBroadcastMessage("LocationId", locationId);

//            for(ScanResult sr : wifiScanList) {
//                Log.i(getClass().getSimpleName(), sr.toString());
//            }
        }

        void displayNearbyDealsNotification(int nearbyDealsCount, String locationId, Context context){
            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
            notificationIntent.putExtra("locationId", locationId);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_deals_notification)
                    .setContentTitle("Buzr")
                    .setContentText(String.format("There are %1$d hot deals around you!", nearbyDealsCount))
                    .setAutoCancel(true);

            mBuilder.setContentIntent(contentIntent);

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // mId allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build());
        }

        private void sendBroadcastMessage(String intentFilterName, String locationId) {
            Intent intent = new Intent(intentFilterName);
            intent.putExtra("location_id", locationId);
            sendBroadcast(intent);
        }
    }
}
