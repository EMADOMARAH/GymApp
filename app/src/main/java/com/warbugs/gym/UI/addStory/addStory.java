package com.warbugs.gym.UI.addStory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.RequiestModels.AddStoryModel;
import com.warbugs.gym.Network.ResponseModels.AddStoryResponse;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.signup.signupScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addStory extends AppCompatActivity {
    TextInputEditText myPost;
    FloatingActionButton postCam;
    Button postBtn;
    ImageView postImg;

    private Uri filePath;
    final static int CAPTURE_REQUEST_CORE = 1;
    String imgString = null;
    String post;

    AddStoryModel addStoryModel;
    ApiInterface apiInterface;
    Retrofit retrofit;
    SharedPreferences preferences;
    String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        preferences = getSharedPreferences("GYM_APP" , Context.MODE_PRIVATE);
        TOKEN = preferences.getString("TOKEN" , "");

        retrofit = new Retrofit.Builder()
                .baseUrl("https://fit-way.mohameek-eg.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        intiAddStoryView();


    }



    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAPTURE_REQUEST_CORE) {

                filePath = data.getData();
                Picasso.get()
                        .load(filePath)
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .fit()
                        .into((ImageView) findViewById(R.id.post_img));
            }

        }

    }

    public void CheckForData(){
        try {
            post = myPost.getText().toString().trim();
            ConvertImgToBase64(filePath.toString());
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        if (post.isEmpty()){
            myPost.setError("Enter Your Story");
            myPost.requestFocus();
        }else if (filePath == null){
            Toast.makeText(this, "Choose Image Please", Toast.LENGTH_SHORT).show();
        }else {

            addStoryModel = new AddStoryModel(post,imgString);
        }
    }

    public void SendStory(){
        apiInterface = retrofit.create(ApiInterface.class);
        //Toast.makeText(this, TOKEN.toString(), Toast.LENGTH_SHORT).show();

        Call<AddStoryResponse> addStory = apiInterface.addStory("Bearer " + TOKEN,addStoryModel);
        addStory.enqueue(new Callback<AddStoryResponse>() {
            @Override
            public void onResponse(Call<AddStoryResponse> call, Response<AddStoryResponse> response) {
                if (response.isSuccessful()){
                    String msg = response.body().getMessage();
                    Toast.makeText(addStory.this, msg.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(addStory.this, "Not Succ", Toast.LENGTH_SHORT).show();
                    try {
                        String error = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(error);
                        String E = jsonObject.getString("message");

                        Toast.makeText(addStory.this, E.toString(), Toast.LENGTH_SHORT).show();
                    }catch (IOException | JSONException e){
                        e.printStackTrace();
                        Toast.makeText(addStory.this, e.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("ex : " + e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<AddStoryResponse> call, Throwable t) {
                Toast.makeText(addStory.this, "Check your internet", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void addStoryOnClick(View view) {
        switch (view.getId()){
            case R.id.mypost:
                break;
            case R.id.post_btn:
                CheckForData();
                SendStory();

                break;
            case R.id.post_cam:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), CAPTURE_REQUEST_CORE);
                break;
            case R.id.back_btn:
                onBackPressed();
                break;
        }
    }

    // Put the image file path into this method
    public void ConvertImgToBase64(String filePath){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bt = null;

        try{
            //bmp = BitmapFactory.decodeFile(filePath);
            //bmp = BitmapFactory.decodeResource(getResources(), R.id.post_img);
            //bmp = BitmapFactory.decodeStream(new ByteArrayInputStream(bos.toByteArray()));
            bmp = ((BitmapDrawable) postImg.getDrawable()).getBitmap();
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 0, bos);
            bt = bos.toByteArray();
            imgString = Base64.encodeToString(bt, Base64.DEFAULT);
            System.out.println("IMAGE" + imgString.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void intiAddStoryView(){
        myPost = findViewById(R.id.mypost);
        postCam = findViewById(R.id.post_cam);
        postBtn = findViewById(R.id.post_btn);
        postImg = findViewById(R.id.post_img);

    }


//    public void SetFontToTitle(TextView txt)
//    {
//        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font.TTF");
//        txt.setTypeface(myTypeface);
//    }
}