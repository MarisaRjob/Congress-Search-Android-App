package com.example.marui.a571hw9_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by marui on 11/29/16.
 */

public class comListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<JSONObject> comJsonList;
    private Context context;


    public comListAdapter(Context context, List<JSONObject> comJsonList){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.comJsonList = comJsonList;
    }

    public class Zujian{
        public TextView comID;
        public TextView comName;
        public TextView comchamber;
        public Button comdetail;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return comJsonList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return comJsonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        comListAdapter.Zujian zujian = null;

        if (convertView == null) {

            zujian= new comListAdapter.Zujian();

            convertView = mInflater.inflate(R.layout.comhouse_listview, null);

            zujian.comID = (TextView)convertView.findViewById(R.id.comhouseID);
            zujian.comName = (TextView)convertView.findViewById(R.id.comhousename);
            zujian.comchamber = (TextView)convertView.findViewById(R.id.comhousechamber);
            zujian.comdetail = (Button) convertView.findViewById(R.id.comhouseview_btn);

            convertView.setTag(zujian);

        }else {

            zujian = (comListAdapter.Zujian)convertView.getTag();
        }

        try{
            //zujian.legimage.setBackgroundResource((Integer)legJsonList.get(position).get(""));
//            String tmp = legJsonList.get(position).getString("first_name");

            zujian.comID.setText(comJsonList.get(position).getString("committee_id"));
            zujian.comName.setText(comJsonList.get(position).getString("name"));
            zujian.comchamber.setText(comJsonList.get(position).getString("chamber"));

            zujian.comdetail.setTag(getItem(position));
            zujian.comdetail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //showInfo();
                    Intent intent = new Intent(context, com_detailActivity.class);
                    intent.putExtra("committee", v.getTag().toString());
                    context.startActivity(intent);
                }
            });}
        catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
