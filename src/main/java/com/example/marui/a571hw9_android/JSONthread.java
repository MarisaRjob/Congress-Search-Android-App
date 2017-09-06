package com.example.marui.a571hw9_android;

import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marui on 11/27/16.
 */

public class JSONthread extends Thread{
    //public JSONthread
    private HttpURLConnection connection;
    private HttpURLConnection bill_connection;
    private HttpURLConnection com_connection;
    private JSONObject finalObject;
    private JSONObject bill_finalObject;
    private JSONObject com_finalObject;
    private List<JSONObject> JsonList = new ArrayList<>();
    private List<JSONObject> billJsonList = new ArrayList<>();
    private List<JSONObject> comJsonList = new ArrayList<>();

    private int number;
    private int kind;
    private Handler listHandler;

    public JSONthread(int number, Handler listHandler) {
        this.number = number;
        this.listHandler = listHandler;
    }

    public JSONthread(int number, int kind, Handler listHandler){
        this.number = number;
        this.kind = kind;
        this.listHandler = listHandler;
    }

    @Override
    public void run() {
        super.run();

        try {

//                    String api_key = "apikey = 0402c5a029694d879c3813aaa36bfd02";
            String u="";
            if(number == 1){
            u = "http://sample-env-2.a2ujzm5xpg.us-east-1.elasticbeanstalk.com/congressHW8.php?query=legislators";}
            else if(number == 2){
                if(kind ==1){
                    u = "http://sample-env-2.a2ujzm5xpg.us-east-1.elasticbeanstalk.com/congressHW8.php?query=activebill";
               }
                else if(kind ==2){
                    u = "http://sample-env-2.a2ujzm5xpg.us-east-1.elasticbeanstalk.com/congressHW8.php?query=newbill";
                }
            }
            else if(number == 3){
                if(kind ==1){
                u = "http://sample-env-2.a2ujzm5xpg.us-east-1.elasticbeanstalk.com/congressHW8.php?query=housecommittee";}
                else if(kind ==2){
                    u="http://sample-env-2.a2ujzm5xpg.us-east-1.elasticbeanstalk.com/congressHW8.php?query=senatecommittee";
                }else if(kind == 3){
                    u = "http://sample-env-2.a2ujzm5xpg.us-east-1.elasticbeanstalk.com/congressHW8.php?query=jointcommittee";
                }
//            if(number == 1){
//                u = "http://104.198.0.197:8080/legislators?apikey=0402c5a029694d879c3813aaa36bfd02&per_page=50";}
//            else if(number == 2){
//                if(kind ==1){
//                    u = "http://104.198.0.197:8080/bills?history.active=true&apikey=c401bf0c6bee4abcae170e8225dce1fe&per_page=50";
//                }
//                else if(kind ==2){
//                    u = "http://104.198.0.197:8080/bills?history.active=false&apikey=c401bf0c6bee4abcae170e8225dce1fe&per_page=50";
//                }
//            }
//            else if(number == 3){
//                if(kind ==1){
//                    u = "http://104.198.0.197:8080/committees?chamber=house&apikey=c401bf0c6bee4abcae170e8225dce1fe&per_page=50";}
//                else if(kind ==2){
//                    u="http://104.198.0.197:8080/committees?chamber=senate&apikey=c401bf0c6bee4abcae170e8225dce1fe&per_page=50";
//                }else if(kind == 3){
//                    u = "http://104.198.0.197:8080/committees?chamber=joint&apikey=c401bf0c6bee4abcae170e8225dce1fe&per_page=50";
//                }
            }
            URL url = new URL(u);
            connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while((line = reader.readLine()) != null){
                buffer.append(line);
            }

            String finalJson = buffer.toString();

            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("results");


            for(int i = 0; i < parentArray.length(); i++)
            {
                JsonList.add(parentArray.getJSONObject(i));
            }



        }catch (Exception e) {
            e.printStackTrace();
        }
        if(number == 2){
            listHandler.obtainMessage(number, kind, 0, JsonList).sendToTarget();
        }else if(number==3){
            listHandler.obtainMessage(number, kind, 0, JsonList).sendToTarget();
        }
        else {
            listHandler.obtainMessage(number, JsonList).sendToTarget();
        }
    }
    //Json
}
