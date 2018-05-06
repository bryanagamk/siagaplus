package bro.id.siagaplus.Activity;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import bro.id.siagaplus.R;

public class AgendaService extends IntentService {
    private NotificationManager alarmNotificationManager;

    public AgendaService() {
        super("AgendaService");
    }

    //TODO" Mengirim msg push notification
    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("Wake Up! Wake Up!");
    }

    private void sendNotification(String msg) {
        Log.d("AgendaService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Agenda").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AgendaService", "Notification sent.");
    }
}