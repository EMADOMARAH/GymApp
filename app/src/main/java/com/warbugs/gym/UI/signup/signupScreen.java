package com.warbugs.gym.UI.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.warbugs.gym.MainActivity;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.RequiestModels.RegisterModel;
import com.warbugs.gym.Network.ResponseModels.Response;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.signIn.signinScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signupScreen extends AppCompatActivity {

    TextInputLayout email_txt, password_txt, repassword_txt, firstName_txt, lastName_txt, phone_txt, bithDate_txt;
    RadioGroup genderRadioGroup;
    RadioButton gender_btn;
    String email,password,firstName,lastName,phone,birthdate ,name;
    int gender;
    int selectedId;

    ApiInterface apiInterface;
    Retrofit retrofit;
    RegisterModel registerModel;
    SharedPreferences preferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);



       preferences = getSharedPreferences("GYM_APP" , Context.MODE_PRIVATE);
       if (preferences.contains("TOKEN")){
           startActivity(new Intent(getApplicationContext() , MainActivity.class));
           finish();
       }

        retrofit = new Retrofit.Builder()
                .baseUrl("https://fit-way.mohameek-eg.com/api/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LinkSignUpViews();

    }

    public void GetDataFromSignUpViews(){
        email = email_txt.getEditText().getText().toString().trim();
        password = password_txt.getEditText().getText().toString().trim();
        firstName = firstName_txt.getEditText().getText().toString().trim();
        lastName = lastName_txt.getEditText().getText().toString().trim();
        name = firstName + " " + lastName;
        phone = phone_txt.getEditText().getText().toString().trim();
        birthdate = bithDate_txt.getEditText().getText().toString().trim();

        selectedId = genderRadioGroup.getCheckedRadioButtonId();
        gender_btn = findViewById(selectedId);
        if (gender_btn.getText().toString().matches("Male")){
            gender = 1;
        }else {
            gender = 0;
        }
    }


    public void LinkSignUpViews(){
        //link view with code variables
        email_txt = findViewById(R.id.email_signup_txt);
        password_txt = findViewById(R.id.pass_signup_txt);
        repassword_txt = findViewById(R.id.re_pass_signup_txt);
        firstName_txt = findViewById(R.id.first_name_signup_txt);
        lastName_txt = findViewById(R.id.last_name_signup_txt);
        phone_txt = findViewById(R.id.phone_signup_txt);
        bithDate_txt = findViewById(R.id.birthdate_signup_txt);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
    }


    public void signUpBtnClicked(View view)
    {
        GetDataFromSignUpViews();
        if(!validatePassword() | !validateEmail() | !ValidateData()){
            return;
        }


        registerModel = new RegisterModel(email,password,firstName,lastName,name,phone,gender,birthdate);
        apiInterface = retrofit.create(ApiInterface.class);

        Call<Response> signUpCall = apiInterface.register(registerModel);
        signUpCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
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

                    String msg = "Welcome " + responseFirstName ;
                    Toast.makeText(signupScreen.this, msg, Toast.LENGTH_SHORT).show();
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

                }else{
                    try {
                        String error = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(error);
                        String E = jsonObject.getString("message");

                        Toast.makeText(signupScreen.this, E.toString(), Toast.LENGTH_SHORT).show();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(signupScreen.this, "Check your internet", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private Boolean ValidateData(){
        if (firstName.isEmpty()){
            firstName_txt.setError("First Name is Required");
            firstName_txt.requestFocus();
            return false;
        }else if (lastName.isEmpty()){
            lastName_txt.setError("Last Name is Required");
            lastName_txt.requestFocus();
            return false;
        }else if (phone.isEmpty()){
            phone_txt.setError("Phone is Required");
            phone_txt.requestFocus();
            return false;
        }else if (birthdate.isEmpty()){
            bithDate_txt.setError("Birthdate is Required");
            bithDate_txt.requestFocus();
            return false;
        }else {
            return true;
        }
    }

    private Boolean validateEmail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        String signupEmail = email_txt.getEditText().getText().toString().trim();
        if (signupEmail.isEmpty()) {
            email_txt.setError("Field cannot be empty");
            Toast.makeText(this, "Email can't be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!signupEmail.matches(emailPattern)) {
            email_txt.setError("Invalid email address");
            email_txt.requestFocus();
            return false;
        } else {
            email_txt.setError(null);
            email_txt.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String signupPassword = password_txt.getEditText().getText().toString().trim();
        String reSignupPassword = repassword_txt.getEditText().getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=/S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (signupPassword.isEmpty()) {
            password_txt.setError("Field cannot be empty");
            password_txt.requestFocus();
            return false;
        } else if (signupPassword.length()<8) {
            password_txt.setError("Password is too weak");
            password_txt.requestFocus();
            return false;
        } else  if (!signupPassword.equals(reSignupPassword)){
            repassword_txt.setError("Password wronge");
            repassword_txt.requestFocus();
            return false;
        }else {
            password_txt.setError(null);
            password_txt.setErrorEnabled(false);
            repassword_txt.setError(null);
            repassword_txt.setErrorEnabled(false);
            return true;
        }
    }

}