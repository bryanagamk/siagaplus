package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.R;
import bro.id.siagaplus.Utils.SharedPref;

public class User11Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CalendarPickerController {

    private static final String LOG_TAG = "JAM" ;
    private static final String TAG = "Jadwal Agenda";
    public String username;
    private SharedPref sharedPref;
    Date date, tgl = null;
    DatabaseHelper db;
    AlarmActivity alarmActivity;
    Calendar startTime1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user11);

        final String txtUsernama = getIntent().getStringExtra("txtUsername");

        Button btnYes = (Button) findViewById(R.id.btnYes);
        Button btnNo = (Button) findViewById(R.id.btnNo);
        TextView mTextView = (TextView) findViewById(R.id.textView2);

        mTextView.setText("Halo, Ibu "+ txtUsernama +"\nApakah Ibu \n sudah hamil?");

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

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User11Activity.this, User2Activity.class);
                intent.putExtra("txtUsernama", txtUsernama);
                startActivity(intent);
                finish();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User11Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
