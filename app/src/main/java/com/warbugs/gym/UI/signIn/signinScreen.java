package com.warbugs.gym.UI.signIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.warbugs.gym.MainActivity;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.RequiestModels.LoginModel;
import com.warbugs.gym.Network.ResponseModels.SignInMessage;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.signup.signupScreen;
import com.warbugs.gym.UI.forgetPassword.forgetPassword;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

    SharedPreferences preferences ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_screen);

        preferences =getSharedPreferences("GYM_APP", Context.MODE_PRIVATE);
        if (preferences.contains("TOKEN")){
            startActivity(new Intent(getApplicationContext() , MainActivity.class));
            //Toast.makeText(this, preferences.getString("TOKEN" , "").toString(), Toast.LENGTH_SHORT).show();
            //System.out.println(preferences.getString("TOKEN" , "").toString());
            String fn = preferences.getString("FirstName" , "");
            Toast.makeText(this, "Hi " + fn.toString(), Toast.LENGTH_SHORT).show();
            finish();
        }else {
            //Toast.makeText(this, "NOOOOOOOOOOO", Toast.LENGTH_SHORT).show();
        }

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

        Call<SignInMessage> signInCall= apiInterface.login(loginModel);
        signInCall.enqueue(new Callback<SignInMessage>() {
            @Override
            public void onResponse(Call<SignInMessage> call, retrofit2.Response<SignInMessage> response) {

                if (response.isSuccessful()){
                    String AccessTocken = response.body().getMessage().getCredentials().getAccessToken().toString();
                    String responseFirstName = response.body().getMessage().getProfile().get(0).getFirstname().toString();
                    String responseLastName = response.body().getMessage().getProfile().get(0).getLastname().toString();
                    String responseName = response.body().getMessage().getProfile().get(0).getName().toString();
                    String responseEmail = response.body().getMessage().getProfile().get(0).getEmail().toString();
                    String responsePhone = response.body().getMessage().getProfile().get(0).getPhone().toString();
                    String responsePhoto = response.body().getMessage().getProfile().get(0).getPhoto().toString();
                    int responseGender = response.body().getMessage().getProfile().get(0).getGender();
                    String responseBirthdate = response.body().getMessage().getProfile().get(0).getBirthdate().toString();

                    String M = "welcome " + responseFirstName;
                    Toast.makeText(signinScreen.this, M, Toast.LENGTH_SHORT).show();
                    //Save token here
                    // Creating an Editor object to edit(write to the file)
                    preferences.edit().putString("TOKEN",AccessTocken).apply();
                    preferences.edit().putString("FirstName", responseFirstName).apply();
                    preferences.edit().putString("LastName", responseLastName).apply();
                    preferences.edit().putString("Name", responseName).apply();
                    preferences.edit().putString("Email", responseEmail).apply();
                    preferences.edit().putString("Phone", responsePhone).apply();
                    preferences.edit().putString("Photo", responsePhoto).apply();
                    preferences.edit().putInt("Gender", responseGender).apply();
                    preferences.edit().putString("BirthDate", responseBirthdate).apply();
                    preferences.edit().commit();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    try {
                        String error = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(error);
                        String E = jsonObject.getString("message");

                        Toast.makeText(signinScreen.this, E.toString(), Toast.LENGTH_SHORT).show();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<SignInMessage> call, Throwable t) {
                Toast.makeText(signinScreen.this, "Check your Internet", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
            }
        });


    }

    private Boolean validateSigninEmail() {
        email = signinEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            signinEmail.setError("cannot be empty");
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