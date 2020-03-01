package com.example.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;

import androidx.annotation.RequiresApi;

public class GpsTrackerAlarmTrigger extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(final Context context, Intent intent) {
        scheduleExactAlarm(context, (AlarmManager) context.getSystemService(Context.ALARM_SERVICE), 1000);

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Service:GpsTrackerWakelock");
        wl.acquire();

        Handler handler = new Handler();
        Runnable periodicUpdate = new Runnable() {
            @Override
            public void run() {
                // whatever you want to do
            }
        };

        handler.post(periodicUpdate);
        wl.release();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void scheduleExactAlarm(Context context, AlarmManager alarms, int interval) {
        int refresh_interval = interval;
        Intent i = new Intent(context, GpsTrackerAlarmTrigger.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        alarms.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 10 * 1000 - SystemClock.elapsedRealtime() % 1000, pi);
    }

    public static void cancelAlarm(Context context, AlarmManager alarms) {
        Intent i = new Intent(context, GpsTrackerAlarmTrigger.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        alarms.cancel(pi);
    }
}