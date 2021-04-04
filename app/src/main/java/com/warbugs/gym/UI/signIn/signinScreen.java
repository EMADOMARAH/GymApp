package com.warbugs.gym.UI.signIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.RequiestModels.LoginModel;
import com.warbugs.gym.Network.ResponseModels.Response;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.signup.signupScreen;
import com.warbugs.gym.UI.forgetPassword.forgetPassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signinScreen extends AppCompatActivity {

    TextInputLayout signinEmail;
    TextInputLayout signinPassword;
    String password , email;
    Retrofit retrofit;
    LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_screen);

        signinEmail  =findViewById(R.id.signin_input_email);
        signinPassword = findViewById(R.id.signin_input_password);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://fit-way.mohameek-eg.com/api/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public void LoginClicked(View view) {
        if(!validateSigninEmail() | !validateSigninPassword()){
            return;
        }
        loginModel = new LoginModel(email,password);
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<Response> signInCall= apiInterface.login(loginModel);
        signInCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                String AccessTocken = response.body().getMessage().getCredentials().getAccessToken().toString();
                String firstName = response.body().getMessage().getProfile().get(0).getFirstname().toString();
                String M = "welcome " + firstName;
                Toast.makeText(signinScreen.this, M, Toast.LENGTH_SHORT).show();
                //Save token here
                SharedPreferences preferences =getSharedPreferences("GYM_APP", Context.MODE_PRIVATE);
                // Creating an Editor object to edit(write to the file)
                preferences.edit().putString("TOKEN",AccessTocken).apply();

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(signinScreen.this, "Check your Internet", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private Boolean validateSigninEmail() {
        email = signinEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            signinEmail.setError("Field cannot be empty");
            Toast.makeText(this, "Email can't be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            signinEmail.setError(null);
            signinEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateSigninPassword() {
        password = signinPassword.getEditText().getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=/S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (password.isEmpty()) {
            signinPassword.setError("Field cannot be empty");
            Toast.makeText(this, "Enter Password please!", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            signinPassword.setError(null);
            signinPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void forgotPassword(View view)
    {
        Intent i = new Intent(getApplicationContext(),forgetPassword.class);
        startActivity(i);
    }


    public void goToSignUpPage(View view)
    {
        Intent i = new Intent(signinScreen.this, signupScreen.class);
        startActivity(i);
    }


}