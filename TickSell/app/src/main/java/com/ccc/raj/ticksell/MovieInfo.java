package com.ccc.raj.ticksell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MovieInfo extends AppCompatActivity {
    private JSONObject BasicInfo;
    private EditText txtName;
    private EditText txtTicketCount;
    private EditText txtAmount;
    private EditText txtDate;
    private EditText txtTime;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundleInfo = getIntent().getExtras();
        BasicInfo = new JSONObject();
        try {
            BasicInfo.put("location",bundleInfo.getString("location"));
            BasicInfo.put("language",bundleInfo.getString("language"));
            BasicInfo.put("theatre",bundleInfo.getString("theatre"));
            BasicInfo.put("movie",bundleInfo.getString("movie"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_movie_info);
        txtName = (EditText) findViewById(R.id.txtName);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtTicketCount = (EditText) findViewById(R.id.txtTicketCount);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navigation = new Intent(MovieInfo.this,OTP_Validation.class);
                JSONObject MovieInfo = new JSONObject();
                try {
                    MovieInfo.put("name",txtName.getText().toString());
                    MovieInfo.put("ticketCount",txtTicketCount.getText().toString());
                    MovieInfo.put("amount",txtAmount.getText().toString());
                    MovieInfo.put("date",txtDate.getText().toString());
                    MovieInfo.put("time",txtTime.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                navigation.putExtra("BasicInfo",BasicInfo.toString());
                navigation.putExtra("MovieInfo",MovieInfo.toString());
                startActivity(navigation);
            }
        });
    }
}
