package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bro.id.siagaplus.R;

public class User3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user3);

        final String txtUsernama = getIntent().getStringExtra("txtUsernama");
        String tglHamil = getIntent().getStringExtra("days");

        Button btnOK = (Button) findViewById(R.id.hitungOk);
        TextView textView = (TextView) findViewById(R.id.textSelamat);
        TextView jumlah = (TextView) findViewById(R.id.bulanbulan);

        textView.setText("Selamat\n Ibu " + txtUsernama + "\nAtas Kehamilannya");
        jumlah.setText(tglHamil);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
