package com.warbugs.gym.UI.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.warbugs.gym.R;
import com.warbugs.gym.UI.myStories.myStories;
import com.warbugs.gym.UI.signIn.signinScreen;

public class profile_fragment extends Fragment {
    View view;
    TextView firstName, lastName;
    TextView name , email,phone,birthdate,gender;

    Button myStories_btn;
    SharedPreferences preferences ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile_fragment, container, false);
        preferences = getActivity().getSharedPreferences("GYM_APP" , Context.MODE_PRIVATE);

        name = view.findViewById(R.id.profile_full_name);
        email = view.findViewById(R.id.profile_email);
        phone = view.findViewById(R.id.profile_phone);
        birthdate = view.findViewById(R.id.profile_birthdate);
        gender = view.findViewById(R.id.profile_gender);

        firstName = view.findViewById(R.id.profile_first_name);
        //lastName  = view.findViewById(R.id.profile_last_name);

        firstName.setText(preferences.getString("FirstName" , " "));
        //lastName.setText(preferences.getString("LastName"," "));
        name.setText(preferences.getString("FirstName","") + " "+preferences.getString("LastName",""));
        email.setText(preferences.getString("Email" , ""));
        phone.setText(preferences.getString("Phone" , ""));
        birthdate.setText(preferences.getString("BirthDate", "").substring(0,10));
        int type = preferences.getInt("Gender",1);
        if ( type== 1){
            gender.setText("Male");
        }else {
            gender.setText("Female");
        }




        myStories_btn = view.findViewById(R.id.my_stories_btn);
        myStories_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), myStories.class);
                startActivity(intent);
            }
        });


        return view;
    }
}