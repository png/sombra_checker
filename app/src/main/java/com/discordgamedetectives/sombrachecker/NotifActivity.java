package com.discordgamedetectives.sombrachecker;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NotifActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
    }
    public void sendNotification(View view) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

        //Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.ic_menu_send);
        mBuilder.setContentTitle("Sombra Checker");
        mBuilder.setContentText("Resting in background.");
        mBuilder.setOngoing(true);

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());
        Intent serviceIntent = new Intent();
        serviceIntent.setAction("com.discordgamedetectives.sombrachecker.NotifService");
        startService(serviceIntent);
    }

}
