package com.ccc.raj.ticksell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class OTP_Validation extends AppCompatActivity {
    private JSONObject MovieInfo;
    private  JSONObject BasicInfo;
    private Button btnPublish;
    private int Seller_Id = 1000;
    private Firebase mRef;
    private EditText txtMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__validation);
        Bundle data = getIntent().getExtras();
        String a = data.getString("MovieInfo");
        txtMobile = (EditText) findViewById(R.id.txtMobile);
        Log.v("Rajkumar","MovieInfo"+a);
        try {
            MovieInfo = new JSONObject(a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String b = data.getString("BasicInfo");
        try {
            BasicInfo = new JSONObject(b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://ticksell-6bb7f.firebaseio.com/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map> map = dataSnapshot.getValue(Map.class);
                Map<String,Integer> seller = map.get("Seller_Id");
                Seller_Id = seller.get("id");
                Seller_Id++;
                Log.v("Rajkumar","data"+Seller_Id);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        btnPublish = (Button) findViewById(R.id.btnPublish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Firebase child1 = mRef.child("Sellers");
                try {
                    child1.child(Seller_Id+"").child("Name").setValue(MovieInfo.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    child1.child(Seller_Id+"").child("Location").setValue(BasicInfo.getString("location"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    child1.child(Seller_Id+"").child("Theatre").setValue(BasicInfo.getString("theatre"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    child1.child(Seller_Id+"").child("Language").setValue(BasicInfo.getString("language"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    child1.child(Seller_Id+"").child("TicketCount").setValue(MovieInfo.getString("ticketCount"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    child1.child(Seller_Id+"").child("Amount").setValue(MovieInfo.getString("amount"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    child1.child(Seller_Id+"").child("MovieName").setValue(BasicInfo.getString("movie"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                child1.child(Seller_Id+"").child("Mobile").setValue(txtMobile.getText().toString());
                try {
                    child1.child(Seller_Id+"").child("Date").setValue(MovieInfo.getString("date"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    child1.child(Seller_Id+"").child("Time").setValue(MovieInfo.getString("time"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Firebase childSell = mRef.child("Seller_Id");
                childSell.child("id").setValue(Seller_Id);
            }
        });
    }
}
