package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bro.id.siagaplus.R;

public class User2Activity extends AppCompatActivity {

    public static final String DATABASE_NAME = "siagaplus_db";

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        TextView textView = (TextView) findViewById(R.id.kapan);
        Button btnOK = (Button) findViewById(R.id.buttonOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User2Activity.this, User3Activity.class);
                startActivity(intent);
            }
        });

    }
}
