package com.warbugs.gym.UI.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.warbugs.gym.R;
import com.warbugs.gym.UI.signIn.signinScreen;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashScreen.this, signinScreen.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}