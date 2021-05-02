package com.warbugs.gym.UI.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.ResponseModels.BmiResponse;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.addStory.addStory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class bmi_fragment extends Fragment {

    View view;
    Spinner genderSpinner;
    EditText weightEdit, hieghtEdit, ageEdit;
    TextView bmi,health,bmiRange;

    double weight,hieght,age;
    String weightString ,hightString,ageString;
    SharedPreferences preferences;

    Button calc_btn;

    ApiInterface apiInterface;
    Retrofit retrofit;
    String TOKEN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_bmi_fragment, container, false);

        preferences = getActivity().getSharedPreferences("GYM_APP", Context.MODE_PRIVATE);
        TOKEN = preferences.getString("TOKEN", "");

        //TOKEN = "8c44f290bamsh3357d1fc0cec729p1db576jsnfdcd588fa753";

        genderSpinner = view.findViewById(R.id.genderSpinner);
        initspinnerfooter();
        initViews();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fitness-calculator.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        calc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calaulateBmi();

            }
        });





        return view;
    }

    private void calaulateBmi() {
        apiInterface = retrofit.create(ApiInterface.class);

        if (checkData()){
            Call<BmiResponse> bmiCalc = apiInterface.calcBmi(
                    TOKEN,
                    Double.parseDouble(weightString),
                    Double.parseDouble(ageString),
                    Double.parseDouble(hightString));
            bmiCalc.enqueue(new Callback<BmiResponse>() {
                @Override
                public void onResponse(Call<BmiResponse> call, Response<BmiResponse> response) {
                    if (response.isSuccessful()){
                        bmi.setText(String.valueOf(response.body().getBmi()));
                        health.setText(String.valueOf(response.body().getHealth()));
                        bmiRange.setText(String.valueOf(response.body().getHealthyBmiRange()));
                    }else {
                        String E = "";
                        try {
                            String error = response.errorBody().string();
                            JSONObject jsonObject = null;
                            jsonObject = new JSONObject(error);
                            E = jsonObject.getString("message");
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(getActivity().getApplicationContext(),E.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BmiResponse> call, Throwable t) {

                }
            });
        }
    }

    private boolean checkData() {
        getDataFromViews();
        if (weightString.isEmpty()){
            weightEdit.setError("Enter Weight");
            weightEdit.requestFocus();
        }else if (hightString.isEmpty()){
            hieghtEdit.setError("Enter Hieght");
            hieghtEdit.requestFocus();
        }else if (ageString.isEmpty()){
            ageEdit.requestFocus();
            ageEdit.setError("Enter Age");
        }else {
            return true;
        }
        return false;
    }

    private void getDataFromViews() {
        weightString = weightEdit.getText().toString();
        hightString = hieghtEdit.getText().toString();
        ageString = ageEdit.getText().toString();
    }

    private void initViews() {
        weightEdit = view.findViewById(R.id.weight_edit_txt);
        hieghtEdit = view.findViewById(R.id.hieght_edit_txt);
        ageEdit = view.findViewById(R.id.age_edit_txt);
        bmi    = view.findViewById(R.id.bmi_txt);
        health = view.findViewById(R.id.health_txt);
        bmiRange = view.findViewById(R.id.bmi_range_txt);

        calc_btn = view.findViewById(R.id.bmi_calc_btn);

    }

    private void initspinnerfooter() {
        String[] items = new String[]{
                "Male" , "Female" };

        genderSpinner.setPrompt("Gender");
        genderSpinner.setAutofillHints("Gender");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
}