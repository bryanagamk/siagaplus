package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bro.id.siagaplus.R;
import bro.id.siagaplus.Utils.AlertDialogManager;
import bro.id.siagaplus.Utils.SessionManager;
import bro.id.siagaplus.Utils.SharedPref;

public class User1Activity extends AppCompatActivity {

    // Username
    EditText txtUsername, txtPassword;

    // login button
    Button btnOK;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManager session;

    // Session Manager Class
    SharedPref status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        // Session Manager
        session = new SessionManager(getApplicationContext());
        status = new SharedPref(getApplicationContext());

        btnOK = (Button) findViewById(R.id.confirm_user);
        txtUsername = (EditText) findViewById(R.id.userNama);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueNama = txtUsername.getText().toString();
                status.createStatus(valueNama);
                Toast.makeText(getApplicationContext(), "Halo, Ibu " +valueNama, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), User11Activity.class);
                intent.putExtra("txtUsername",valueNama);
                startActivity(intent);
                finish();
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        // super.onBackPressed(); commented this line in order to disable back press
//        //Write your code here
//        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
//    }
}
