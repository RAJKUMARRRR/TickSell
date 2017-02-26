package com.ccc.raj.ticksell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SellersList extends AppCompatActivity {

    private ListView listSellers;
    private Firebase mRef;
    public ArrayList<Seller> seller_list_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers_list);
        Firebase.setAndroidContext(this);
        listSellers = (ListView) findViewById(R.id.listSellers);
        seller_list_model = new ArrayList<Seller>();
        mRef = new Firebase("https://ticksell-6bb7f.firebaseio.com/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map> map = dataSnapshot.getValue(Map.class);
                Map<String,Map> lang = map.get("Sellers");
                Log.v("Rajkumar","Languages:"+lang);
                Iterator<Map.Entry<String, Map>> entries = lang.entrySet().iterator();
                while (entries.hasNext()) {
                    Log.v("Rajkumar","Inside while");
                    Map.Entry<String, Map> entry = entries.next();
                    String key = entry.getKey()+"";
                    Map<String,String> value = entry.getValue();
                    String theatre = value.get("Theatre");
                    String amount = value.get("Amount")+"";
                    String location = value.get("Location");
                    String ticketCount = value.get("TicketCount")+"";
                    String date = value.get("Date")+"";
                    String time = value.get("Time")+"";
                    String language = value.get("Language");
                    String name = value.get("name");
                    Seller seller = new Seller(theatre,ticketCount,amount,date,time,name,location,language);
                    Log.v("Rajkume","test"+seller.language);
                    seller_list_model.add(seller);
                }
                Seller[] temp = new Seller[seller_list_model.size()];
                temp = seller_list_model.toArray(temp);
                CustomAdapterList c = new CustomAdapterList(SellersList.this,temp);
                listSellers.setAdapter(c);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
