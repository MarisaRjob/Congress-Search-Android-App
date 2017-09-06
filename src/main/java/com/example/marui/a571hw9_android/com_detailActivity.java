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

public class com_detailActivity extends AppCompatActivity {
    private String com_id, com_name,com_chamber,com_parentcom,com_contact,com_office;
    private TextView textcom_id,textcom_name, textcom_chamber,textcom_parentcom,textcom_contact,textcom_office;
    private JSONObject com_detailJson;
    private ImageView img_star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String committee = this.getIntent().getStringExtra("committee");
        try {
            com_detailJson = new JSONObject(committee);
            com_id = com_detailJson.getString("committee_id");
            com_name =com_detailJson.getString("name");
            com_chamber =com_detailJson.getString("chamber");
            com_parentcom  =com_detailJson.getString("parent_committee_id");
            com_contact  =com_detailJson.getString("phone");
            com_office = com_detailJson.getString("office");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  Button returnbutton = (Button)this.findViewById(R.id.returnbutton);
        textcom_id = (TextView) findViewById(R.id.textViewcomID);
        textcom_name = (TextView) findViewById(R.id.textViewcomname);
        textcom_chamber= (TextView) findViewById(R.id.textViewcomchamber);
        textcom_parentcom = (TextView) findViewById(R.id.textViewcomparentcom);
        textcom_contact = (TextView) findViewById(R.id.textViewcomcontact);
        textcom_office = (TextView) findViewById(R.id.textViewcomoffice);


        textcom_id.setText(com_id);
        textcom_name.setText(com_name);
        textcom_chamber.setText(com_chamber);
        textcom_parentcom.setText(com_parentcom);
        textcom_contact.setText(com_contact);
        textcom_office.setText(com_office);
        img_star = (ImageView)findViewById(R.id.com_detailstar) ;
        img_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean tmp = false;
                for(int i =0; i <MainActivity.favcommittee.size(); i++){
                    try {
                        if (MainActivity.favcommittee.get(i).getString("committee_id").equals(com_detailJson.getString("committee_id"))){
                            MainActivity.favcommittee.remove(i);
                            tmp = true;
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (tmp == false) {
                    MainActivity.favcommittee.add(com_detailJson);
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
