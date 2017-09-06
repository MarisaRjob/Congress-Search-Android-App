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

public class billListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<JSONObject> billJsonList;
    private Context context;


    public billListAdapter(Context context, List<JSONObject> billJsonList){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.billJsonList = billJsonList;
    }

    public class Zujian{
        public TextView billID;
        public TextView billIntroduce;
        public TextView billTitle;
        public Button billdetail;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return billJsonList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return billJsonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        billListAdapter.Zujian zujian = null;

        if (convertView == null) {

            zujian= new billListAdapter.Zujian();

            convertView = mInflater.inflate(R.layout.actbill_listview, null);
            zujian.billID = (TextView)convertView.findViewById(R.id.actbillID);
            zujian.billTitle = (TextView)convertView.findViewById(R.id.actbilltitle);
            zujian.billIntroduce = (TextView)convertView.findViewById(R.id.actbillintroduce);
            zujian.billdetail = (Button) convertView.findViewById(R.id.actbillview_btn);

            convertView.setTag(zujian);

        }else {

            zujian = (billListAdapter.Zujian)convertView.getTag();
        }

        try{
            //zujian.legimage.setBackgroundResource((Integer)legJsonList.get(position).get(""));
//            String tmp = legJsonList.get(position).getString("first_name");

            zujian.billID.setText(billJsonList.get(position).getString("bill_id"));
            zujian.billTitle.setText(billJsonList.get(position).getString("official_title"));
            zujian.billIntroduce.setText(billJsonList.get(position).getString("introduced_on"));
            zujian.billdetail.setTag(getItem(position).toString());
            zujian.billdetail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, bill_detailActivity.class);
                    intent.putExtra("bill", v.getTag().toString());
                    context.startActivity(intent);

                }
            });}
        catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
