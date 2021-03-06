package bro.id.siagaplus.Activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.List;

import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.Model.Agenda;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    Ringtone ringtone;
    DatabaseHelper db;

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message
        AlarmActivity inst = AlarmActivity.instance();
        List<Agenda> allAgenda = db.getAllAgenda();
        for (Agenda agenda : allAgenda){
            Log.d("onCreate: Agenda Title", agenda.getTitle());
            inst.setAlarmText(agenda.getTitle());
        }


        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //After 1s stop the alarm
        // You can adjust the time depending upon your requirement.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ringtone.stop();
            }
        }, 10000);

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
