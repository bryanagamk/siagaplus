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
    private static final String TAG = "jadwalAgenda";
    public String username;
    private SharedPref sharedPref;
    Date date, tgl = null;
    DatabaseHelper db;
    AlarmActivity alarmActivity;
    private Calendar startTime1;

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

        textView.setText("Selamat\n Ibu " + txtUsernama + "\nAtas\nKehamilannya");
        jumlah.setText(tglHamil + " Bulan");
        aWeeks.setText(minggu + " Minggu");

        db = new DatabaseHelper(getApplicationContext());

        sharedPref = new SharedPref(getApplicationContext());
        HashMap<String, String> user = sharedPref.getUserDetails();
        String tgl = user.get(SharedPref.KEY_TGL);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

//        alarmActivity = (AlarmActivity) getApplicationContext();

        try{
            date = format.parse(tgl);
        } catch (ParseException e){
            e.printStackTrace();
        }

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.set(Calendar.MONTH, 0);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 5);


        if (date != null){
            List<CalendarEvent> eventLists = new ArrayList<>();
            /*mockListPass(eventList);*/
            jadwalagenda(eventLists);

        } else {
            List<CalendarEvent> eventList = new ArrayList<>();
            agendakosong(eventList);
        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User3Activity.this, MainActivity.class);
                intent.putExtra("username", txtUsernama);
                startActivity(intent);
                finish();
            }
        });
    }

    private void agendakosong(List<CalendarEvent> eventList) {

        for (int i = 0; i < (360); i++){
            seharigigi(i, eventList);
            seminggugigi(i, eventList);
            Log.d(TAG, "agendaagendaan: i : " + i);
        }

    }

    private void jadwalagenda(List<CalendarEvent> eventList){

        Calendar mulaihamil = Calendar.getInstance();
        Calendar lahiran = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        mulaihamil.setTime(date);
        lahiran = mulaihamil;
        lahiran.add(Calendar.MONTH, 9);
        long deff = lahiran.getTimeInMillis() - today.getTimeInMillis();
        long sisaHari = deff / (24 * 60* 60 * 1000);

        Log.d(TAG, "jadwalagenda: today " + today.getTime());
        Log.d(TAG, "jadwalagenda: lahiran " + lahiran.getTime());
        Log.d(TAG, "jadwalagenda: deff " + deff);
        Log.d(TAG, "jadwalagenda: sisahari " + sisaHari);

        int i =0, temp1, temp2;
        if (sisaHari >= 180){                                            // Trimester 1
            for (; i < sisaHari-180; i++){
                seharigigi(i, eventList);
                seminggugigi(i, eventList);
                sebulanbidan(i, eventList);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester1 selesai");
            sisaHari = sisaHari - i;
        }
        if (sisaHari > 90 && sisaHari <= 180){
            sisaHari += i;// Trimester 2
            int temp = (int) (sisaHari-90);
            for (; i < temp; i++ ){
                duaminggugigi(i, eventList);
                duaminggubidan(i, eventList);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester2 selesai");
            sisaHari = sisaHari - i;
        }
        if(sisaHari <= 90) {
            int temp = (int) (sisaHari + i);// Trimester 1
            for (; i < temp; i++){
                seminggubidan(i, eventList);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester3 selesai");
        }
    }

    private void seharigigi(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "seharigigi: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.DATE, i);
        Calendar endTime1 = Calendar.getInstance();

        Log.d(String.valueOf(startTime1.getTime()), "mockListPass: startTime");
        endTime1 = startTime1;
        BaseCalendarEvent event1 = new BaseCalendarEvent("Yuk, Sikat gigi setelah sarapan & sebelum tidur malam", "Untuk periksa!", "",
                ContextCompat.getColor(this, R.color.colorPrimaryDark), startTime1, endTime1, true);
        eventList.add(event1);

        // Insert SQLite
        db.insertAgenda("Yuk, Sikat gigi setelah sarapan & sebelum tidur malam", String.valueOf(startTime1.getTime()));

        // Set Alarm
    }
    private void seminggugigi(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "seminggugigi: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.DATE, i);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            Log.d(String.valueOf(startTime1.getTime()), "mockListPass: startTime");
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Gigi", "Untuk periksa!", "Klinik/RS",
                    ContextCompat.getColor(this, R.color.agenda1), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
            db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }

    }
    private void seminggubidan(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "seminggubidan: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.DATE, i);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            Log.d(String.valueOf(startTime1.getTime()), "mockListPass: startTime");
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda2), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }
    }
    private void duaminggugigi(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "duaminggugigi: ");
        startTime1 = Calendar.getInstance();
        Log.d(TAG, "duaminggugigi: var startTime 1 : " + startTime1.getTime());
        startTime1.add(Calendar.DATE, i);

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && startTime1.get(Calendar.WEEK_OF_MONTH) % 2 != 0){

            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Gigi", "Untuk periksa!", "Klinik / RS",
                    ContextCompat.getColor(this, R.color.agenda3), startTime1, startTime1, true);
            eventList.add(event1);

            // Insert SQLite
            db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

            // Set Alarm
        }
    }
    private void duaminggubidan(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "duaminggubidan: ");
        startTime1 = Calendar.getInstance();
        Log.d(TAG, "duaminggubidan: var startTime 1 : " + startTime1.getTime());
        startTime1.add(Calendar.DATE, i);

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && startTime1.get(Calendar.WEEK_OF_MONTH) % 2 != 0){

            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda4), startTime1, startTime1, true);
            eventList.add(event1);

            // Insert SQLite
            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }

    }
    private void sebulanbidan(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "sebulanbidan: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.DATE, i);

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda5), startTime1, startTime1, true);
            eventList.add(event1);

            // Insert SQLite
            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onDaySelected(DayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        }
    }
}
