package com.example.marui.a571hw9_android;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class bill_detailActivity extends AppCompatActivity {
    private String bill_id, bill_title,bill_type,bill_sponsorfname,bill_sponsorlname,bill_statussit,bill_sponsortitle,bill_sponsor,bill_chamber,bill_status,bill_introduceon,bill_conurl,bill_versionstatus,bill_url;
    private TextView textbill_id,textbill_title, textbill_type,textbill_sponsor,textbill_chamber,textbill_status,textbill_introduceon,textbill_conurl,textbill_versionstatus,textbill_url;
    private ImageView img_star;
    private JSONObject leg_detailJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String bill = this.getIntent().getStringExtra("bill");
        try {
             leg_detailJson = new JSONObject(bill);
            bill_id = leg_detailJson.getString("bill_id");
            bill_title =leg_detailJson.getString("official_title");
            bill_type =leg_detailJson.getString("bill_type");
            bill_sponsorfname =leg_detailJson.getJSONObject("sponsor").getString("first_name");
            bill_sponsorlname =leg_detailJson.getJSONObject("sponsor").getString("last_name");
            bill_sponsortitle =leg_detailJson.getJSONObject("sponsor").getString("title");
            bill_sponsor=bill_sponsortitle+". "+bill_sponsorlname+", "+bill_sponsorfname;
            bill_chamber =leg_detailJson.getString("chamber");
            bill_statussit  =leg_detailJson.getJSONObject("history").getString("active");
            if(bill_statussit=="true"){
                bill_status = "Active";
            }else if(bill_statussit == "flase"){
                bill_status = "New";
            }
            bill_introduceon =leg_detailJson.getString("introduced_on");
            bill_conurl =leg_detailJson.getJSONObject("urls").getString("congress");
            bill_versionstatus =leg_detailJson.getJSONObject("last_version").getString("version_name");
            bill_url =leg_detailJson.getJSONObject("urls").getString("pdf");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  Button returnbutton = (Button)this.findViewById(R.id.returnbutton);
        textbill_id = (TextView) findViewById(R.id.textViewbillID);
        textbill_title = (TextView) findViewById(R.id.textViewbilltitle);
        textbill_type = (TextView) findViewById(R.id.textViewbilltype);
        textbill_sponsor = (TextView) findViewById(R.id.textViewbillsponsor);
        textbill_chamber= (TextView) findViewById(R.id.textViewbillchamber);
        textbill_status = (TextView) findViewById(R.id.textViewbillstatus);
        textbill_introduceon = (TextView) findViewById(R.id.textViewbillintroduceon);
        textbill_conurl = (TextView) findViewById(R.id.textViewbillurl);
        textbill_versionstatus = (TextView) findViewById(R.id.textViewbillversionstatus);
        textbill_url = (TextView) findViewById(R.id.textViewbill_billurl);
        img_star = (ImageView)findViewById(R.id.bill_detailstar) ;


        textbill_id.setText(bill_id);
        textbill_title.setText(bill_title);
        textbill_type.setText(bill_type);
        textbill_sponsor.setText(bill_sponsor);
        textbill_chamber.setText(bill_chamber);
        textbill_status.setText(bill_status);
        textbill_introduceon.setText(bill_introduceon);
        textbill_conurl.setText(bill_conurl);
        textbill_versionstatus.setText(bill_versionstatus);
        textbill_url.setText(bill_url);

        img_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean tmp = false;
                for(int i =0; i <MainActivity.favbill.size(); i++){
                    try {
                        if (MainActivity.favbill.get(i).getString("bill_id").equals(leg_detailJson.getString("bill_id"))){
                            MainActivity.favbill.remove(i);
                            tmp = true;
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (tmp == false) {
                    MainActivity.favbill.add(leg_detailJson);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }

