package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.R;

public class User1Activity extends AppCompatActivity {

    private DatabaseHelper db;
//    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        final DatabaseHelper mDBHelper = new DatabaseHelper(getBaseContext());

        Button btnOK = (Button) findViewById(R.id.confirm_user);
        final EditText editText = (EditText) findViewById(R.id.userNama);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String valueNama = editText.getText().toString();
                Toast.makeText(User1Activity.this, "Halo, Ibu " + valueNama, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(User1Activity.this, User11Activity.class);
                startActivity(intent);
            }
        });

    }
}
