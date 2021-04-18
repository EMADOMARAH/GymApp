package com.warbugs.gym.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.warbugs.gym.R;
import com.warbugs.gym.UI.myStories.myStories;
import com.warbugs.gym.UI.signIn.signinScreen;

public class profile_fragment extends Fragment {
    View view;

    Button myStories_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile_fragment, container, false);

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