package drawable;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NotifService extends IntentService {
    public NotifService() {
        super("NotifService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            URL url = new URL ("http://amomentincrime.s3.amazonaws.com/index.html");
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
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
