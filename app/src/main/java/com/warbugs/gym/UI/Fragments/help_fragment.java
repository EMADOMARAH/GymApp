package com.warbugs.gym.UI.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.warbugs.gym.R;
import com.warbugs.gym.UI.signIn.signinScreen;

public class help_fragment extends Fragment {
    View bigView;

    Button logout,send;
    ImageButton callUs,emailUs,chat;
    EditText nameEditText, emailEditText,messageEditText;
    SharedPreferences preferences ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bigView =  inflater.inflate(R.layout.fragment_help_fragment, container, false);

        preferences = this.getActivity().getSharedPreferences("GYM_APP", Context.MODE_PRIVATE);

        linkViews();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(getActivity(), signinScreen.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().isEmpty()){
                    nameEditText.setError("Enter Name");
                    nameEditText.requestFocus();
                }else if (emailEditText.getText().toString().isEmpty()){
                    emailEditText.requestFocus();
                    emailEditText.setError("Enter Email");
                }else if (messageEditText.getText().toString().isEmpty()){
                    messageEditText.setError("Enter Message");
                    messageEditText.requestFocus();
                }else {
                    Toast.makeText(getContext(), "Thanks For Contacting With US", Toast.LENGTH_SHORT).show();
                    nameEditText.setText("");
                    emailEditText.setText("");
                    messageEditText.setText("");

                }
            }
        });

        emailUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSite = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://fit-way.mohameek-eg.com/"));
                startActivity(openSite);
            }
        });

        callUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "125-668-886"));
                startActivity(openPhone);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return bigView;
    }

    private void linkViews() {
        logout = bigView.findViewById(R.id.logout);

        callUs = bigView.findViewById(R.id.call_us_btn);
        emailUs= bigView.findViewById(R.id.email_us_btn);
        chat   = bigView.findViewById(R.id.chat_btn);
        send   = bigView.findViewById(R.id.send_btn);

        nameEditText = bigView.findViewById(R.id.full_name_edit_text);
        emailEditText= bigView.findViewById(R.id.email_edit_text);
        messageEditText = bigView.findViewById(R.id.message_edit_text);



    }


}