package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.R;
import bro.id.siagaplus.Utils.SharedPref;

public class User3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CalendarPickerController {

    private static final String LOG_TAG = "JAM" ;
    public String username;
    private SharedPref sharedPref;
    Date date, tgl = null;
    DatabaseHelper db;
    private AlarmActivity alarmActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user3);

        final String txtUsernama = getIntent().getStringExtra("txtUsernama");
        final String tglHamil = getIntent().getStringExtra("bulan");
        final String minggu = getIntent().getStringExtra("minggu");

        Button btnOK = (Button) findViewById(R.id.hitungOk);
        TextView textView = (TextView) findViewById(R.id.textSelamat);
        TextView jumlah = (TextView) findViewById(R.id.bulanbulan);
        TextView aWeeks = (TextView) findViewById(R.id.angkaMinggu);

        textView.setText("Selamat\n Ibu " + txtUsernama + "\nAtas");
        jumlah.setText(tglHamil + " Bulan");
        aWeeks.setText(minggu + " Minggu");

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User3Activity.this, MainActivity.class);
                intent.putExtra("username", txtUsernama);
                startActivity(intent);
                finish();
            }
        });

        db = new DatabaseHelper(getApplicationContext());

        sharedPref = new SharedPref(getApplicationContext());
        HashMap<String, String> user = sharedPref.getUserDetails();
        String tgl = user.get(SharedPref.KEY_TGL);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try{
            date = format.parse(tgl);
        } catch (ParseException e){
            e.printStackTrace();
        }

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        alarmActivity = AlarmActivity.instance();

        minDate.set(Calendar.MONTH, 0);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 5);


        if (date != null){
            List<CalendarEvent> eventList = new ArrayList<>();
            mockListPass(eventList);

            AgendaCalendarView mAgendaCalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        } else {
            List<CalendarEvent> eventList = new ArrayList<>();
            mockList(eventList);

            AgendaCalendarView mAgendaCalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        }
    }

    private void mockList(List<CalendarEvent> eventList) {

        int i = 0;
        while(i <= 10){
            Calendar startTime1 = Calendar.getInstance();
            startTime1.add(Calendar.DATE, i);
            Calendar endTime1 = Calendar.getInstance();
            Log.d(String.valueOf(startTime1.get(Calendar.DAY_OF_WEEK)), String.valueOf(Calendar.MONDAY));
            if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                Log.d("COK","Masuk If");
                startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                endTime1 = startTime1;
                BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Gigi", "Untuk periksa!", "Iceland",
                        ContextCompat.getColor(this, R.color.agenda1), startTime1, endTime1, true);
                eventList.add(event1);
                Log.d(String.valueOf(startTime1.getTime()), "mockList: startTime");

                // Set Alarm
                long id = db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                String dbTgl = db.getAgenda(id).getDate();

                SimpleDateFormat format = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy", Locale.US);

                try{
                    tgl = format.parse(dbTgl);
                    Log.d(String.valueOf(tgl), "onCreate: format.parse");
                } catch (ParseException e){
                    e.printStackTrace();
                }

                Log.d("Enter", "onCreate: Tes");
                Log.d(String.valueOf(tgl), "onCreate: tgl");

                Calendar cal = Calendar.getInstance();
                Calendar alarm = Calendar.getInstance();
                cal.setTime(tgl);

                Log.d(String.valueOf(cal.getTime()), "onCreate: cal.getTime");
                Log.d(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), "onCreate: cal.get(Calendar.DAY_OF_YEAR)");
                Log.d(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "onCreate: cal.get(Calendar.HOUR_OF_DAY)");
                Log.d(String.valueOf(cal.get(Calendar.MINUTE)), "onCreate: cal.get(Calendar.MINUTE)");

                alarmActivity.setAlarmOn(cal);
            }
            i++;
        }
    }

    private void mockListPass(List<CalendarEvent> eventList) {

        int i = 0;
        Calendar mulaiHamil = Calendar.getInstance();
        Calendar melahirkan = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        mulaiHamil.setTime(date);
        melahirkan = mulaiHamil;
        melahirkan.add(Calendar.MONTH, 9);
        long deff = melahirkan.getTimeInMillis() - today.getTimeInMillis();
        long aDay = deff / (24 * 60* 60 * 1000);
        if (aDay <= 90){
            Log.d(String.valueOf(aDay), "mockListPass Trisemester 1");
            while(i <= aDay){
                Calendar startTime1 = Calendar.getInstance();
                startTime1.add(Calendar.DATE, i);
                Calendar endTime1 = Calendar.getInstance();

                if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                    Log.d("COK","Masuk If");
                    startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                    startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                    startTime1.add(Calendar.MONTH, 1);
                    endTime1 = startTime1;
                    BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Gigi", "Untuk periksa!", "RS",
                            ContextCompat.getColor(this, R.color.agenda2), startTime1, endTime1, true);
                    eventList.add(event1);
                    db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                    // Set Alarm
                    long id = db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                    String dbTgl = db.getAgenda(id).getDate();

                    SimpleDateFormat format = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy", Locale.US);

                    try{
                        tgl = format.parse(dbTgl);
                        Log.d(String.valueOf(tgl), "onCreate: format.parse");
                    } catch (ParseException e){
                        e.printStackTrace();
                    }

                    Log.d("Enter", "onCreate: Tes");
                    Log.d(String.valueOf(tgl), "onCreate: tgl");

                    Calendar cal = Calendar.getInstance();
                    Calendar alarm = Calendar.getInstance();
                    cal.setTime(tgl);

                    Log.d(String.valueOf(cal.getTime()), "onCreate: cal.getTime");
                    Log.d(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), "onCreate: cal.get(Calendar.DAY_OF_YEAR)");
                    Log.d(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "onCreate: cal.get(Calendar.HOUR_OF_DAY)");
                    Log.d(String.valueOf(cal.get(Calendar.MINUTE)), "onCreate: cal.get(Calendar.MINUTE)");

                    alarmActivity.setAlarmOn(cal);
                }
                i++;
            }
            while(i <= aDay){
                Calendar startTime1 = Calendar.getInstance();
                startTime1.add(Calendar.DATE, i);
                Calendar endTime1 = Calendar.getInstance();
                if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
                    Log.d("COK","Masuk If");
                    startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                    startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                    startTime1.add(Calendar.MONTH, 1);
                    endTime1 = startTime1;
                    BaseCalendarEvent event1 = new BaseCalendarEvent("Periksa ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                            ContextCompat.getColor(this, R.color.agenda3), startTime1, endTime1, true);
                    eventList.add(event1);
                    db.insertAgenda("Periksa ke Bidan", String.valueOf(startTime1.getTime()));

                    // Set Alarm
                    long id = db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                    String dbTgl = db.getAgenda(id).getDate();

                    SimpleDateFormat format = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy", Locale.US);

                    try{
                        tgl = format.parse(dbTgl);
                        Log.d(String.valueOf(tgl), "onCreate: format.parse");
                    } catch (ParseException e){
                        e.printStackTrace();
                    }

                    Log.d("Enter", "onCreate: Tes");
                    Log.d(String.valueOf(tgl), "onCreate: tgl");

                    Calendar cal = Calendar.getInstance();
                    Calendar alarm = Calendar.getInstance();
                    cal.setTime(tgl);

                    Log.d(String.valueOf(cal.getTime()), "onCreate: cal.getTime");
                    Log.d(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), "onCreate: cal.get(Calendar.DAY_OF_YEAR)");
                    Log.d(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "onCreate: cal.get(Calendar.HOUR_OF_DAY)");
                    Log.d(String.valueOf(cal.get(Calendar.MINUTE)), "onCreate: cal.get(Calendar.MINUTE)");

                    alarmActivity.setAlarmOn(cal);
                }
                i++;
            }
        } else if (aDay > 90 || aDay <= 180){
            Log.d(String.valueOf(aDay), "mockListPass Trisemester 2");
            while(i <= aDay){
                Calendar startTime1 = Calendar.getInstance();
                startTime1.add(Calendar.DATE, i);
                Calendar endTime1 = Calendar.getInstance();

                if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                    Log.d("COK","Masuk If");
                    startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                    startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                    startTime1.add(Calendar.DATE, 14);
                    endTime1 = startTime1;
                    BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Gigi", "Untuk periksa!", "RS",
                            ContextCompat.getColor(this, R.color.agenda4), startTime1, endTime1, true);
                    eventList.add(event1);
                    db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                    // Set Alarm
                    long id = db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                    String dbTgl = db.getAgenda(id).getDate();

                    SimpleDateFormat format = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy", Locale.US);

                    try{
                        tgl = format.parse(dbTgl);
                        Log.d(String.valueOf(tgl), "onCreate: format.parse");
                    } catch (ParseException e){
                        e.printStackTrace();
                    }

                    Log.d("Enter", "onCreate: Tes");
                    Log.d(String.valueOf(tgl), "onCreate: tgl");

                    Calendar cal = Calendar.getInstance();
                    Calendar alarm = Calendar.getInstance();
                    cal.setTime(tgl);

                    Log.d(String.valueOf(cal.getTime()), "onCreate: cal.getTime");
                    Log.d(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), "onCreate: cal.get(Calendar.DAY_OF_YEAR)");
                    Log.d(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "onCreate: cal.get(Calendar.HOUR_OF_DAY)");
                    Log.d(String.valueOf(cal.get(Calendar.MINUTE)), "onCreate: cal.get(Calendar.MINUTE)");

                    alarmActivity.setAlarmOn(cal);

                }
                i++;
            }

            while(i <= aDay){
                Calendar startTime1 = Calendar.getInstance();
                startTime1.add(Calendar.DATE, i);
                Calendar endTime1 = Calendar.getInstance();
                Log.d(String.valueOf(startTime1.get(Calendar.DAY_OF_WEEK)), String.valueOf(Calendar.MONDAY));
                if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
                    Log.d("COK","Masuk If");
                    startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                    startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                    startTime1.add(Calendar.DATE, 14);
                    endTime1 = startTime1;
                    BaseCalendarEvent event1 = new BaseCalendarEvent("Periksa ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                            ContextCompat.getColor(this, R.color.agenda5), startTime1, endTime1, true);
                    eventList.add(event1);
                    db.insertAgenda("Periksa ke Bidan", String.valueOf(startTime1.getTime()));

                    // Set Alarm
                    long id = db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                    String dbTgl = db.getAgenda(id).getDate();

                    SimpleDateFormat format = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy", Locale.US);

                    try{
                        tgl = format.parse(dbTgl);
                        Log.d(String.valueOf(tgl), "onCreate: format.parse");
                    } catch (ParseException e){
                        e.printStackTrace();
                    }

                    Log.d("Enter", "onCreate: Tes");
                    Log.d(String.valueOf(tgl), "onCreate: tgl");

                    Calendar cal = Calendar.getInstance();
                    Calendar alarm = Calendar.getInstance();
                    cal.setTime(tgl);

                    Log.d(String.valueOf(cal.getTime()), "onCreate: cal.getTime");
                    Log.d(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), "onCreate: cal.get(Calendar.DAY_OF_YEAR)");
                    Log.d(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "onCreate: cal.get(Calendar.HOUR_OF_DAY)");
                    Log.d(String.valueOf(cal.get(Calendar.MINUTE)), "onCreate: cal.get(Calendar.MINUTE)");

                    alarmActivity.setAlarmOn(cal);
                }
                i++;
            }

        } else {
            Log.d(String.valueOf(aDay), "mockListPass Trisemester 3");
            while(i <= aDay){
                Calendar startTime1 = Calendar.getInstance();
                startTime1.add(Calendar.DATE, i);
                Calendar endTime1 = Calendar.getInstance();
                Log.d(String.valueOf(startTime1.get(Calendar.DAY_OF_WEEK)), String.valueOf(Calendar.MONDAY));
                if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                    Log.d("COK","Masuk If");
                    startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                    startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                    endTime1 = startTime1;
                    BaseCalendarEvent event1 = new BaseCalendarEvent("Periksa ke Bidan", "Untuk periksa!", "Klinik (KIA)",
                            ContextCompat.getColor(this, R.color.colorPrimaryDark), startTime1, endTime1, true);
                    eventList.add(event1);
                    db.insertAgenda("Periksa ke Bidan", String.valueOf(startTime1.getTime()));

                    // Set Alarm
                    long id = db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

                    String dbTgl = db.getAgenda(id).getDate();

                    SimpleDateFormat format = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy", Locale.US);

                    try{
                        tgl = format.parse(dbTgl);
                        Log.d(String.valueOf(tgl), "onCreate: format.parse");
                    } catch (ParseException e){
                        e.printStackTrace();
                    }

                    Log.d("Enter", "onCreate: Tes");
                    Log.d(String.valueOf(tgl), "onCreate: tgl");

                    Calendar cal = Calendar.getInstance();
                    Calendar alarm = Calendar.getInstance();
                    cal.setTime(tgl);

                    Log.d(String.valueOf(cal.getTime()), "onCreate: cal.getTime");
                    Log.d(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), "onCreate: cal.get(Calendar.DAY_OF_YEAR)");
                    Log.d(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "onCreate: cal.get(Calendar.HOUR_OF_DAY)");
                    Log.d(String.valueOf(cal.get(Calendar.MINUTE)), "onCreate: cal.get(Calendar.MINUTE)");

                    alarmActivity.setAlarmOn(cal);
                }
                i++;
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onDaySelected(DayItem dayItem) {

    }

    @Override
    public void onEventSelected(CalendarEvent event) {

    }

    @Override
    public void onScrollToDate(Calendar calendar) {

    }
}
