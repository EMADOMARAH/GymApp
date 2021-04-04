package com.warbugs.gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.warbugs.gym.UI.Fragments.bmi_fragment;
import com.warbugs.gym.UI.Fragments.calory_fragment;
import com.warbugs.gym.UI.Fragments.help_fragment;
import com.warbugs.gym.UI.Fragments.home_fragment;
import com.warbugs.gym.UI.Fragments.profile_fragment;
import com.warbugs.gym.UI.addStory.addStory;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading defalt fragment
        loadFragment(new home_fragment());


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home:
//                        fm.beginTransaction().replace(R.id.container, new home).commit();
//                        break;
//                    case R.id.grads:
//
//                        break;
//                    case R.id.Profile:
//
//                        break;
//                    case R.id.help:
//
//                        break;
//
//                }
//                return true;
//            }
//        });




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.home:
                fragment = new home_fragment();
                break;

            case R.id.grads:
                fragment = new calory_fragment();
                break;

            case R.id.Profile:
                fragment = new profile_fragment();
                break;

            case R.id.help:
                fragment = new help_fragment();
                break;

            case R.id.bmi:
                fragment = new bmi_fragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}