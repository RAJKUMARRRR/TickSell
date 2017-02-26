package com.ccc.raj.ticksell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button sellTickets;
    public  Button btnBuyTickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sellTickets = (Button) findViewById(R.id.btnSellTickets);
        sellTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,BasicInfo.class);
                startActivity(i);
            }
        });
        btnBuyTickets = (Button) findViewById(R.id.btnBuyTickets);
        btnBuyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav = new Intent(MainActivity.this,SellersList.class);
                startActivity(nav);
            }
        });
    }
}
