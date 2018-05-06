package bro.id.siagaplus.Activity;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.R;

public class AlarmActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    Calendar calendar = null;
    DatabaseHelper db;
    Date tgl;

    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);

        db = new DatabaseHelper(getApplicationContext());
        Agenda id1 = db.getAgenda(1);
        String date = id1.getDate();
        String title = id1.getTitle();

        Log.d(date, "onCreate: date");
        Log.d(title, "onCreate: title");

        SimpleDateFormat format = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy", Locale.US);

        try{
            tgl = format.parse(date);
            Log.d(String.valueOf(tgl), "onCreate: format.parse");
        } catch (ParseException e){
            e.printStackTrace();
        }

        Log.d(String.valueOf(tgl), "onCreate: tgl");
        Calendar cal = Calendar.getInstance();
        Calendar alarm = Calendar.getInstance();
        cal.setTime(tgl);
//        alarm.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR));
//        alarm.set(Calendar.DATE, cal.DATE);
        Log.d(String.valueOf(cal.getTime()), "onCreate: cal.getTime");
        Log.d(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), "onCreate: cal.get(Calendar.DAY_OF_YEAR)");
        Log.d(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "onCreate: cal.get(Calendar.HOUR_OF_DAY)");
        Log.d(String.valueOf(cal.get(Calendar.MINUTE)), "onCreate: cal.get(Calendar.MINUTE)");

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("MainActivity", "Alarm On");

            Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, myIntent, 0);

            calendar = Calendar.getInstance();


            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());


            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);

        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MainActivity", "Alarm Off");
        }
    }

    public void setAlarmOn(Calendar cal) {
        Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, myIntent, 0);

        calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));


        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }
}
