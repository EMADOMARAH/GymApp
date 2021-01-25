package com.warbugs.gym.signIn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.warbugs.gym.R;
import com.warbugs.gym.Signup.signupScreen;

public class signinScreen extends AppCompatActivity {

    TextView signuptxt;
    Button signinbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_screen);
        signuptxt  =findViewById(R.id.signuptxt);
        signinbtn = findViewById(R.id.loginbtn);

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signinScreen.this, signupScreen.class);
                startActivity(i);

            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(signinScreen.this, "hhhhhh", Toast.LENGTH_SHORT).show();
            }
        });

    }


}