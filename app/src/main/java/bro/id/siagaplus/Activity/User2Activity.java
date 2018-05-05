package bro.id.siagaplus.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import bro.id.siagaplus.R;

public class User2Activity extends AppCompatActivity {

    EditText etTgl;
    Date tglHaid;
    public Calendar tglHamil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        final String txtUsernama = getIntent().getStringExtra("txtUsernama");

        TextView textView = (TextView) findViewById(R.id.kapan);
        Button btnOK = (Button) findViewById(R.id.buttonOK);
        etTgl = findViewById(R.id.etTgl);

        textView.setText("Kapan\nibu " + txtUsernama +"\nterakhir kali Haid?");

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.YEAR, year);
                updateLabel();
            }

            void updateLabel() {
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                etTgl.setText(sdf.format(myCalendar.getTime()));
                tglHaid = myCalendar.getTime();
            }
        };

        etTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(User2Activity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        tglHamil.setTime(tglHaid);
        Calendar today = Calendar.getInstance();
        long diff = today.getTimeInMillis() - tglHamil.getTimeInMillis();
        final long days = diff / (24 * 60 * 60 * 1000);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User2Activity.this, User3Activity.class);
                intent.putExtra("txtUsernama", txtUsernama);
                intent.putExtra("days", days);
                startActivity(intent);
            }
        });


    }


}
