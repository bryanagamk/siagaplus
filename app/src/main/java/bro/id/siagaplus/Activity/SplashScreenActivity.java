package bro.id.siagaplus.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import bro.id.siagaplus.R;
import bro.id.siagaplus.Utils.SharedPref;

public class SplashScreenActivity extends AppCompatActivity {
    // Session Manager Class
    SharedPref status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        status = new SharedPref(getApplicationContext());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                status.checkStatus();
                finish();
            }
        }, 3000L); //3000 L = 3 detik
    }
}
