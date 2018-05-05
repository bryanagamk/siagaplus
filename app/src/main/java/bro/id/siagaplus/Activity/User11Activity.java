package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bro.id.siagaplus.R;

public class User11Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user11);

        final String txtUsernama = getIntent().getStringExtra("txtUsername");

        Button btnYes = (Button) findViewById(R.id.btnYes);
        Button btnNo = (Button) findViewById(R.id.btnNo);
        TextView mTextView = (TextView) findViewById(R.id.textView2);

        mTextView.setText("Halo, Ibu "+ txtUsernama +"\nApakah Ibu \n sudah hamil?");

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User11Activity.this, User2Activity.class);
                intent.putExtra("txtUsernama", txtUsernama);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User11Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
