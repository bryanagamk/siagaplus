package bro.id.siagaplus.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.Model.User;
import bro.id.siagaplus.R;

public class User2Activity extends AppCompatActivity {

    private DatabaseHelper db;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        TextView textView = (TextView) findViewById(R.id.kapan);
        String nama = user.getName();
        String txt = "Kapan \n Terakhir kali\n Ibu" + nama + "mengalami Haid?";
        textView.setText(txt);

    }
}
