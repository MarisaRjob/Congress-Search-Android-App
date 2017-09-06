package com.example.marui.a571hw9_android;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
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
 * Created by marui on 11/27/16.
 */

public class legListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<JSONObject> legJsonList;
    private Context context;


    public legListAdapter(Context context, List<JSONObject> legJsonList){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.legJsonList = legJsonList;
    }

    public class Zujian{
        public ImageView legimage;
        public TextView legname;
        public TextView legparty;
        public TextView legstate;
        public TextView legdistrict;
        public Button legdetail;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return legJsonList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return legJsonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian = null;

        if (convertView == null) {

            zujian= new Zujian();

            convertView = mInflater.inflate(R.layout.bystate_listview, null);
            zujian.legimage = (ImageView)convertView.findViewById(R.id.bystateimg);
            zujian.legname = (TextView)convertView.findViewById(R.id.bystatename);
            zujian.legparty = (TextView)convertView.findViewById(R.id.bystateparty);
            zujian.legstate = (TextView) convertView.findViewById(R.id.bystatestate);
            zujian.legdistrict = (TextView) convertView.findViewById(R.id.bystatedistrict);
            zujian.legdetail = (Button) convertView.findViewById(R.id.bystateview_btn);
//
            convertView.setTag(zujian);

        }else {

            zujian = (Zujian)convertView.getTag();
        }

        try{
        //zujian.legimage.setBackgroundResource((Integer)legJsonList.get(position).get(""));
//            String tmp = legJsonList.get(position).getString("first_name");
            String imgURL = "https://theunitedstates.io/images/congress/original/"+legJsonList.get(position).get("bioguide_id")+".jpg";
            Picasso.with(context)
                    .load(imgURL)
                    .resize(50, 50)
                    .centerCrop()
                    .into(zujian.legimage);
            Picasso.with(context).load(imgURL).into(zujian.legimage);
            zujian.legname.setText(legJsonList.get(position).getString("last_name")+", "+legJsonList.get(position).getString("first_name"));
            zujian.legparty.setText("("+ legJsonList.get(position).getString("party")+")");
            zujian.legstate.setText(legJsonList.get(position).getString("state_name"));
            zujian.legdistrict.setText(" - District "+legJsonList.get(position).getString("district"));
//获得这条json数据，把它变成string
            zujian.legdetail.setTag(getItem(position).toString());
        zujian.legdetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, leg_detailActivity.class);
                intent.putExtra("legislator", (String) v.getTag());
                context.startActivity(intent);

            }
        });}
        catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
