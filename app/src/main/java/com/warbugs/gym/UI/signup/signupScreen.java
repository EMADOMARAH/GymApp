package com.warbugs.gym.UI.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.RequiestModels.RegisterModel;
import com.warbugs.gym.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signupScreen extends AppCompatActivity {

    TextInputLayout email;
    TextInputLayout password;
    TextInputLayout repassword;
    ApiInterface apiInterface;
    Retrofit retrofit;
    RegisterModel registerModel;

 String img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://fit-way.mohameek-eg.com/api/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        //link view with code variables
        email = findViewById(R.id.email_signup_txt);
        password = findViewById(R.id.pass_signup_txt);
        repassword = findViewById(R.id.re_pass_signup_txt);
    }


    public void signUpBtnClicked(View view)
    {
        if(!validatePassword() | !validateEmail()){
            return;
        }
        registerModel= new RegisterModel(email.getEditText().getText().toString(),password.getEditText().getText().toString(),repassword.getEditText().getText().toString(),"fin","lan","name","phon",img,0,"bth");

        Signup(registerModel);

    }

    private void Signup(RegisterModel registerModel){





    }

    private Boolean validateEmail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        String signupEmail = email.getEditText().getText().toString().trim();
        if (signupEmail.isEmpty()) {
            email.setError("Field cannot be empty");
            Toast.makeText(this, "Email can't be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!signupEmail.matches(emailPattern)) {
            email.setError("Invalid email address");
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String signupPassword = password.getEditText().getText().toString().trim();
        String reSignupPassword = repassword.getEditText().getText().toString().trim();
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
            password.setError("Field cannot be empty");
            Toast.makeText(this, "Enter Password please!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (signupPassword.length()<8) {
            password.setError("Password is too weak");
            Toast.makeText(this, "Password is too weak ", Toast.LENGTH_SHORT).show();
            return false;
        } else  if (!signupPassword.equals(reSignupPassword)){
            repassword.setError("Password wronge");
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            repassword.setError(null);
            repassword.setErrorEnabled(false);
            return true;
        }
    }



}