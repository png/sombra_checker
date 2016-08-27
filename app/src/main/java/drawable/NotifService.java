package drawable;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.discordgamedetectives.sombrachecker.MainActivity;
import com.discordgamedetectives.sombrachecker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class NotifService extends IntentService {
    public static volatile boolean shouldContinue = true;
    ArrayList<Character> pageHTMLold;
    ArrayList<Character> pageHTMLnew;
    public NotifService() {
        super("NotifService");
    }
    boolean killwhile;

    @Override
    protected void onHandleIntent(Intent intent) {
        mScreenStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                stopSelf();
                killwhile = true;
                context.stopService(intent);
                stopSelf();

            }
        };
        oldnum = "1.0000";
        while(shouldContinue == true){
            try {
                checksite();
                Thread.sleep(90000);

            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
        }

    }
    String oldnum;
    public void checksite () {
        pageHTMLold = null;
        pageHTMLnew = null;

        String numberOnly = null;
        if (killwhile == false) {
            try {

                //URL url = new URL("http://www.time.gov/");
                URL url = new URL("http://amomentincrime.s3.amazonaws.com/index.html");
                URLConnection yc = url.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                yc.getInputStream()));
                String inputLine;
                StringBuilder builder = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                    builder.append(inputLine.trim());
                in.close();
                String htmlPage = builder.toString();

                numberOnly = htmlPage.replaceAll("[^0-9]", "");
                numberOnly = numberOnly.substring(7);
                char[] digits1 = numberOnly.toCharArray();
                if (digits1.length < 6 && digits1.length >= 5) {
                    numberOnly = digits1[0] + "." + digits1[1] + digits1[2] + digits1[3] + digits1[4];

                }
                if (digits1.length < 7 && digits1.length >= 6) {
                    numberOnly = digits1[0] + "." + digits1[1] + digits1[2] + digits1[3] + digits1[4] + digits1[5];
                }
                if (oldnum != null) {
                    char[] digitsNum1 = numberOnly.toCharArray();
                    char[] digitsNum2 = oldnum.toCharArray();

                    if (digitsNum2.length > 0 && digitsNum1.length > 0)
                        for (int i = 0; i < digitsNum1.length - 1; i++) {
                            if (digitsNum1[i] != digitsNum2[i]) {
                                notifSend(numberOnly);

                                break;
                            }
                        }
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        oldnum = numberOnly+"";




        }




    public void notifSend(String num){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

        //Create the intent that’ll fire when the user taps the notification//

        Intent intent2 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent2, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.wnotif);
        mBuilder.setContentTitle("Sombra Checker");
        mBuilder.setContentText("Site updated! Now "+num+"%!");
        mBuilder.setOngoing(false);

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(002, mBuilder.build());
        Intent serviceIntent = new Intent(this, NotifService.class);
        stopService(serviceIntent);
    }
    BroadcastReceiver mScreenStateReceiver;



    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mScreenStateReceiver, filter);

    }
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
                //action for sms received
            }
            else if(action.equals(android.telephony.TelephonyManager.ACTION_PHONE_STATE_CHANGED)){
                //action for phone state changed
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();



    }
}
