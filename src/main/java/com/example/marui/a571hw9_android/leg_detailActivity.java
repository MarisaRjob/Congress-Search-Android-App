package com.example.marui.a571hw9_android;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class leg_detailActivity extends AppCompatActivity {

    private String leg_party,leg_firstname,facebook_url,leg_lastname,leg_facebook,leg_middlename,leg_name,leg_email,leg_chamber,leg_contact,leg_startterm,leg_endterm,leg_term,leg_office,leg_state,leg_fax,leg_birthday;
    private TextView textleg_party,textleg_name,textleg_email,textleg_chamber,textleg_contact,textleg_startterm,textleg_endterm,textleg_term,textleg_office,textleg_state,textleg_fax,textleg_birthday;
    private ImageView img_star,img_facebook,img_twitter,img_global;
private URL url;
    private JSONObject leg_detailJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String legislator = this.getIntent().getStringExtra("legislator");
        try {
            leg_detailJson = new JSONObject(legislator);
            leg_party = leg_detailJson.getString("party");
            leg_firstname = leg_detailJson.getString("first_name");
            leg_lastname = leg_detailJson.getString("last_name");
            leg_middlename = leg_detailJson.getString("middle_name");
            leg_name = leg_lastname+","+leg_middlename+","+leg_firstname;
            leg_email = leg_detailJson.getString("oc_email");
            leg_chamber = leg_detailJson.getString("chamber");
            leg_contact = leg_detailJson.getString("phone");
            leg_startterm = leg_detailJson.getString("term_start");
            leg_endterm = leg_detailJson.getString("term_end");
            leg_office = leg_detailJson.getString("office");
            leg_state = leg_detailJson.getString("state");
            leg_fax = leg_detailJson.getString("fax");
            leg_birthday = leg_detailJson.getString("birthday");
            leg_facebook= leg_detailJson.getString("facebook_id");
            facebook_url ="https://www.facebook.com/"+leg_facebook;
//            url= new URL(facebook_url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  Button returnbutton = (Button)this.findViewById(R.id.returnbutton);

        textleg_party = (TextView) findViewById(R.id.textViewRD);
        textleg_name = (TextView) findViewById(R.id.textViewname);
        textleg_email = (TextView) findViewById(R.id.textViewemail);
        textleg_chamber = (TextView)findViewById(R.id.textViewchamber);
        textleg_contact = (TextView)findViewById(R.id.textViewcontact);
        textleg_startterm= (TextView)findViewById(R.id.textViewstartterm);
        textleg_endterm = (TextView)findViewById(R.id.textViewendterm);
        textleg_office =  (TextView)findViewById(R.id.textViewoffice);
        textleg_state= (TextView)findViewById(R.id.textViewstate);
        textleg_fax= (TextView)findViewById(R.id.textViewfax);
        textleg_birthday= (TextView)findViewById(R.id.textViewbirthday);
        img_star = (ImageView)findViewById(R.id.leg_detailstar) ;
        img_facebook = (ImageView)findViewById(R.id.leg_detailfacebook);

        textleg_name.setText(leg_name);
        textleg_party.setText(leg_party);
        textleg_email.setText(leg_email);
        textleg_chamber.setText(leg_chamber);
        textleg_contact.setText(leg_contact);
        textleg_startterm.setText(leg_startterm);
        textleg_endterm.setText(leg_endterm);
        textleg_office.setText(leg_office);
        textleg_state.setText(leg_state);
        textleg_fax.setText(leg_fax);
        textleg_birthday.setText(leg_birthday);

        img_facebook.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(url);
//                startActivity(intent);
            }
        });

        img_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean tmp = false;
                for(int i =0; i <MainActivity.favlegislator.size(); i++){
                    try {
                        if (MainActivity.favlegislator.get(i).getString("bioguide_id").equals(leg_detailJson.getString("bioguide_id"))){
                            MainActivity.favlegislator.remove(i);
                            tmp = true;
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (tmp == false) {
                    MainActivity.favlegislator.add(leg_detailJson);
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
