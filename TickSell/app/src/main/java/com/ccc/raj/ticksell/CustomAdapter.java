package com.ccc.raj.ticksell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Raj on 2/26/2017.
 */

class CustomAdapterList extends ArrayAdapter<Seller>
{
    Context context;
    Seller[] seller;
    CustomAdapterList(Context c,Seller[] sell)
    {
        super(c,R.layout.sellers_list_template,R.id.txtTitle,sell);
        this.context=c;
        seller = sell;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sellers_list_template, parent, false);
        }
        //ImageView img= (ImageView) v.findViewById(R.id.imageView);
        TextView t1= (TextView) v.findViewById(R.id.txtTitle);
        TextView t2= (TextView) v.findViewById(R.id.txtDescription);
        TextView t3= (TextView) v.findViewById(R.id.txtStatus);
        TextView t4= (TextView) v.findViewById(R.id.txtAmount);
        //img.setImageResource(R.drawable.mario1);
        t1.setText(seller[position].theatreName);
        t2.setText(seller[position].location);
        t3.setText(seller[position].ticketCount);
        t4.setText(seller[position].amount);
        return v;
    }
}
class Seller
{
    public String theatreName;
    public String ticketCount;
    public String amount;
    public String date;
    public String time;
    public String name;
    public String location;
    public String language;

    Seller(String theatreName,String ticketCount,String amount,String date,String time,String name,String location,String language)
    {
        this.theatreName = theatreName;
        this.ticketCount = ticketCount;
        this.amount = amount;
        this.location = location;
        this.date = date;
        this.name = name;
        this.time = time;
        this.language = language;

    }
}



