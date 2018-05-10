package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

public class AgendaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CalendarPickerController {

    private static final String LOG_TAG = "JAM" ;
    private static final String TAG = "Jadwal Agenda";
    public String username;
    private SharedPref sharedPref;
    Date date, tgl = null;
    DatabaseHelper db;
    Calendar startTime1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.set(Calendar.MONTH, 0);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 5);


        if (date != null){
            List<CalendarEvent> eventList = new ArrayList<>();
//            eventList = jadwalagenda(eventList);

            AgendaCalendarView mAgendaCalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

            Log.d(TAG, "onCreate: eventlist " + eventList);
            Log.d(TAG, "onCreate: minDate " + minDate.getTime());
            Log.d(TAG, "onCreate: maxDate " + maxDate.getTime());
            Log.d(TAG, "onCreate: locale " + Locale.getDefault());
            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        } else {
            List<CalendarEvent> eventList = new ArrayList<>();
            eventList = agendakosong(eventList);

            AgendaCalendarView mAgendaCalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        }

    }

    private List<CalendarEvent> agendakosong(List<CalendarEvent> eventList) {

        for (int i = 0; i < 100; i++){
            seharigigi(i, eventList);
            seminggugigi(i, eventList);
            Log.d(TAG, "agendaagendaan: i : " + i);
        }

        return eventList;
    }

    private List<CalendarEvent> jadwalagenda(List<CalendarEvent> eventList){

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

        if (sisaHari <= 90){                                            // Trimester 3
            for (int i = 0; i < sisaHari; i++){
                eventList = seminggubidan(i, eventList);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester3 selesai");
        } else if (sisaHari > 90 && sisaHari <= 180){                   // Trimester 2
            int temp = (int) (sisaHari-90);
            for (int i = 0; i < temp; i++ ){
                eventList = duaminggugigi(i, eventList);
                eventList = duaminggubidan(i, eventList);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester2 selesai");
            for (int i = 0; i < 90; i++){
                eventList = seminggubidantri2(i, eventList, temp);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester3 selesai");
        } else {                                                        // Trimester 1
            for (int i = 0; i < (sisaHari-180); i++){
                eventList = seharigigi(i, eventList);
                eventList = seminggugigi(i, eventList);
                eventList = sebulanbidan(i, eventList);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester1 selesai");
            int temp1 = (int) (sisaHari-180);
            for (int i = 0; i < 90; i++ ){
                eventList = duaminggugigitri3(i, eventList, temp1);
                eventList = duaminggubidantri3(i, eventList, temp1);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester2 selesai");
            int temp2 = (int) (sisaHari- 90 - temp1);
            for (int i = 0; i < 90; i++){
                eventList = seminggubidantri3(i, eventList, temp2);
                Log.d(TAG, "jadwalagenda: i : " + i);
            }
            Log.d(TAG, "jadwalagenda: trimester3 selesai");
        }

        return eventList;
    }

    private List<CalendarEvent> seharigigi(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "seharigigi: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.DATE, i);
        Calendar endTime1 = Calendar.getInstance();

        startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
        Log.d(String.valueOf(startTime1.getTime()), "mockListPass: startTime");
        startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
        endTime1 = startTime1;
        BaseCalendarEvent event1 = new BaseCalendarEvent("Yuk, Sikat gigi setelah sarapan & sebelum tidur malam", "Untuk periksa!", "",
                ContextCompat.getColor(this, R.color.colorPrimaryDark), startTime1, endTime1, true);
        eventList.add(event1);

        // Insert SQLite
//        db.insertAgenda("Yuk, Sikat gigi setelah sarapan & sebelum tidur malam", String.valueOf(startTime1.getTime()));

        // Set Alarm
        return eventList;
    }
    private List<CalendarEvent> seminggugigi(int i, List<CalendarEvent> eventList){
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
//            db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }

        return eventList;
    }
    private List<CalendarEvent> seminggubidan(int i, List<CalendarEvent> eventList){
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
//            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }
        return eventList;
    }
    private List<CalendarEvent> seminggubidantri2(int i, List<CalendarEvent> eventList, int temp){
        Log.d(TAG, "seminggubidan: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.DATE, temp);
        Log.d(TAG, "seminggubidantri2: temp " + temp);
        startTime1.add(Calendar.DATE, i + temp);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            Log.d(String.valueOf(startTime1.getTime()), "mockListPass: startTime");
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda2), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
//            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }

        return eventList;
    }
    private List<CalendarEvent> seminggubidantri3(int i, List<CalendarEvent> eventList, int temp){
        Log.d(TAG, "seminggubidan: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.DATE, i + temp);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda2), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
//            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm
        }
        return eventList;
    }
    private List<CalendarEvent> duaminggugigi(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "duaminggugigi: ");
        startTime1 = Calendar.getInstance();
        Log.d(TAG, "duaminggugigi: var startTime 1 : " + startTime1.getTime());
        startTime1.add(Calendar.DATE, i*2);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Gigi", "Untuk periksa!", "Klinik / RS",
                    ContextCompat.getColor(this, R.color.agenda3), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
//            db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

            // Set Alarm
        }
        return eventList;
    }
    private List<CalendarEvent> duaminggugigitri3(int i, List<CalendarEvent> eventList, int temp){
        Log.d(TAG, "duaminggugigi: ");
        startTime1 = Calendar.getInstance();
        Log.d(TAG, "duaminggugigi: var startTime 1 : " + startTime1.getTime());
        startTime1.add(Calendar.DATE, i*2 + temp);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Dokter Gigi", "Untuk periksa!", "Klinik / RS",
                    ContextCompat.getColor(this, R.color.agenda3), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
//            db.insertAgenda("Pergi ke Dokter Gigi", String.valueOf(startTime1.getTime()));

            // Set Alarm
        }
        return eventList;
    }
    private List<CalendarEvent> duaminggubidan(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "duaminggubidan: ");
        startTime1 = Calendar.getInstance();
        Log.d(TAG, "duaminggubidan: var startTime 1 : " + startTime1.getTime());
        startTime1.add(Calendar.DATE, i*2);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda4), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
//            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }

        return eventList;

    }
    private List<CalendarEvent> duaminggubidantri3(int i, List<CalendarEvent> eventList, int temp){
        Log.d(TAG, "duaminggubidan: ");
        startTime1 = Calendar.getInstance();
        Log.d(TAG, "duaminggubidan: var startTime 1 : " + startTime1.getTime());
        startTime1.add(Calendar.DATE, i*2 + temp);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda4), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
//            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }
        return eventList;
    }
    private List<CalendarEvent> sebulanbidan(int i, List<CalendarEvent> eventList){
        Log.d(TAG, "sebulanbidan: ");
        startTime1 = Calendar.getInstance();
        startTime1.add(Calendar.MONTH, 1);
        startTime1.add(Calendar.DATE, i);
        Calendar endTime1 = Calendar.getInstance();

        if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){

            startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
            startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
            endTime1 = startTime1;
            BaseCalendarEvent event1 = new BaseCalendarEvent("Pergi ke Bidan", "Untuk periksa!", "Klinik(KIA)",
                    ContextCompat.getColor(this, R.color.agenda5), startTime1, endTime1, true);
            eventList.add(event1);

            // Insert SQLite
//            db.insertAgenda("Pergi ke Bidan", String.valueOf(startTime1.getTime()));

            // Set Alarm

        }
        return eventList;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else if (id == R.id.notes) {
            Intent intent = new Intent(this, NoteActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.agenda) {
            Intent intent = new Intent(this, AgendaActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.checklist) {

            Intent intent = new Intent(this, CheckListActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.artikel) {
            Intent intent = new Intent(this, ArtikelActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.aboutus) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
