package com.ccc.raj.ticksell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.FirebaseApp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BasicInfo extends AppCompatActivity {

    private Firebase mRef;
    private ArrayList<String> locations;
    private ArrayList<String> languages;
    private ArrayList<String> theatres;
    private AutoCompleteTextView txtLanguage;
    private AutoCompleteTextView txtLocations;
    private AutoCompleteTextView txtTheatres;
    private Button btnNext;
    private EditText txtMovieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        Firebase.setAndroidContext(this);
        languages = new ArrayList<String>();
        locations = new ArrayList<String>();
        theatres = new ArrayList<String>();
        txtLanguage = (AutoCompleteTextView) findViewById(R.id.txtLanguages);
        txtLocations = (AutoCompleteTextView) findViewById(R.id.txtLocations);
        txtTheatres = (AutoCompleteTextView) findViewById(R.id.txtTheatres);
        txtMovieName = (EditText) findViewById(R.id.txtMovieName);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav = new Intent(BasicInfo.this,MovieInfo.class);
                String locationName = txtLocations.getText().toString();
                String theatreName = txtTheatres.getText().toString();
                String languageName = txtLanguage.getText().toString();
                String movieName  = txtMovieName.getText().toString();
                nav.putExtra("location",locationName);
                nav.putExtra("language",languageName);
                nav.putExtra("theatre",theatreName);
                nav.putExtra("movie",movieName);
                startActivity(nav);
            }
        });
        mRef =  new Firebase("https://ticksell-6bb7f.firebaseio.com/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map> map = dataSnapshot.getValue(Map.class);
                Map<String,String> lang = map.get("Languages");
                Log.v("Rajkumar","Languages:"+lang);
                Iterator<Map.Entry<String, String>> entries = lang.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    languages.add(value);
                    //Toast.makeText(BasicInfo.this,value,Toast.LENGTH_SHORT).show();
                }
                Map<String,String> loc = map.get("Locations");
                Log.v("Rajkumar","Languages:"+loc);
                Iterator<Map.Entry<String, String>> entriesLoc = loc.entrySet().iterator();
                while (entriesLoc.hasNext()) {
                    Map.Entry<String, String> entry = entriesLoc.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    locations.add(value);
                    //Toast.makeText(BasicInfo.this,value,Toast.LENGTH_SHORT).show();
                }
                Map<String,String> theatss = map.get("Theatres");
                Log.v("Rajkumar","Languages:"+theatss);
                Iterator<Map.Entry<String, String>> entriesTheatss = theatss.entrySet().iterator();
                while (entriesTheatss.hasNext()) {
                    Map.Entry<String, String> entry = entriesTheatss.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    theatres.add(value);
                    //Toast.makeText(BasicInfo.this,value,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //String[] temp = new String[languages.size()];
        //temp = languages.toArray(temp);
        ArrayAdapter<String> adapterLanguages = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,languages);
        txtLanguage.setAdapter(adapterLanguages);
        txtLanguage.setThreshold(1);
        ArrayAdapter<String> adapterLocations = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,locations);
        txtLocations.setAdapter(adapterLocations);
        txtLocations.setThreshold(1);
        ArrayAdapter<String> adapterTheatres = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,theatres);
        txtTheatres.setAdapter(adapterTheatres);
        txtTheatres.setThreshold(1);
    }
}
