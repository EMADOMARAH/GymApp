package com.warbugs.gym.UI.addStory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.warbugs.gym.R;

public class addStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);


    }

    public void BackOneStep(View view) {
        onBackPressed();
    }

    public void CameraClicked(View view) {
        Toast.makeText(this, "Will Be Avalable SOON", Toast.LENGTH_SHORT).show();
    }

    public void PostButtonClicked(View view) {
        Toast.makeText(this, "Post Clicked", Toast.LENGTH_SHORT).show();
    }

//    public void SetFontToTitle(TextView txt)
//    {
//        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font.TTF");
//        txt.setTypeface(myTypeface);
//    }
}