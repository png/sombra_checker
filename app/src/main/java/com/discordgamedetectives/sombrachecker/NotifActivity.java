package com.discordgamedetectives.sombrachecker;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import drawable.NotifService;

public class NotifActivity extends AppCompatActivity {
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    boolean persistentn = true;
    NotificationManager mNotificationManager;
    public void sendNotification(View view) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

        //Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(this, NotifActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.notif);
        mBuilder.setContentTitle("Sombra Checker");
        mBuilder.setContentText("Watching in the background.");
        mBuilder.setOngoing(persistentn);

        mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());
        NotifService.shouldContinue=true;
        serviceIntent = new Intent(this, NotifService.class);
        startService(serviceIntent);

        isMyServiceRunning(NotifService.class);

    }
    public void stopNotification(View view) {
        Intent intent = new Intent();
        sendBroadcast(intent, "");
        persistentn = false;
        sendNotification(view);
        mNotificationManager.cancel(001);
        stopService(serviceIntent);
        NotifService.shouldContinue = false;

    }
    public void genjiPhoto(View view){
        Toast.makeText(NotifActivity.this,"Butt.",
                Toast.LENGTH_SHORT).show();

    }

}
