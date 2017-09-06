package com.example.marui.a571hw9_android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.marui.a571hw9_android.R.id.billtabs;
import static com.example.marui.a571hw9_android.R.id.billvp_view;
import static com.example.marui.a571hw9_android.R.id.legbystatelist;
import static com.example.marui.a571hw9_android.R.id.legbystatelist;
import static com.example.marui.a571hw9_android.R.id.leghouselist;
import static com.example.marui.a571hw9_android.R.id.legsenatelist;
import static com.example.marui.a571hw9_android.R.id.legtabs;
import static com.example.marui.a571hw9_android.R.id.legvp_view;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv_legistors;
    TextView tv_bills;
    TextView tv_committees;
    TextView tv_favorites;

    RelativeLayout rlayout;
//tab
    private TabLayout legTabLayout;
    private ViewPager legViewPager;
    private LayoutInflater legInflater;
    private List<String> legTitleList = new ArrayList<>();//页卡标题集合
    private View legview1, legview2, legview3;//页卡视图
    private List<View> legViewList = new ArrayList<>();//页卡视图集合

    private TabLayout billTabLayout;
    private ViewPager billViewPager;
    private LayoutInflater billInflater;
    private List<String> billTitleList = new ArrayList<>();//页卡标题集合
    private View billview1, billview2;//页卡视图
    private List<View> billViewList = new ArrayList<>();

    private TabLayout comTabLayout;
    private ViewPager comViewPager;
    private LayoutInflater comInflater;
    private List<String> comTitleList = new ArrayList<>();//页卡标题集合
    private View comview1, comview2, comview3;//页卡视图
    private List<View> comViewList = new ArrayList<>();

    private TabLayout favTabLayout;
    private ViewPager favViewPager;
    private LayoutInflater favInflater;
    private List<String> favTitleList = new ArrayList<>();//页卡标题集合
    private View favview1, favview2, favview3;//页卡视图
    private List<View> favViewList = new ArrayList<>();

    private ListView favlegListView;
    private ListView favbillListView;
    private ListView favcomListView;
    // tab



//    private LinearLayout legislator_main;
//    private LinearLayout bill_main;
//    private LinearLayout committee_main;
//    private LinearLayout favorite_main;

    private legislatorFragment legislatorFragment;
    private billFragment billFragment;
    private committeeFragment committeeFragment;
    private favoriteFragment favoriteFragment;

//json

    private List<JSONObject> legJsonList;
    private List<JSONObject> billJsonList;
    private List<JSONObject> comJsonList;
    private List<JSONObject> favJsonList;

    //json
//listview

    private ListView legbystate_listView = null;
    private ListView leghouse_listView = null;
    private ListView legsenate_listView = null;
    private ListView billactive_listView = null;
    private ListView billnew_listView = null;
    private ListView comhouse_listView = null;
    private ListView comsenate_listView = null;
    private ListView comjoint_listView = null;
    private ListView favleg_listView = null;
    private ListView favbill_listView = null;
    private ListView favcom_listView = null;
    //listview
    private String[] alphabet_letter = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    Map<Integer,String> alphabetMap;
    TextView SelectedAlphabet;
    Map<String, Integer> mapIndex;


    public static List<JSONObject> favlegislator;
    public static List<JSONObject> favbill;
    public static List<JSONObject> favcommittee;

    public static legListAdapter ladapter;
    public static billListAdapter badapter;
    public static comListAdapter cadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        favlegislator = new ArrayList<JSONObject>();
        favbill = new ArrayList<JSONObject>();
        favcommittee = new ArrayList<JSONObject>();

 //       tv_legistors = (TextView) findViewById(R.id.text_legislators);
//        tv_bills = (TextView) findViewById(R.id.text_bills);
//        tv_committees = (TextView) findViewById(R.id.text_committees);
//        tv_favorites = (TextView) findViewById(R.id.text_favorites);

        rlayout = (RelativeLayout) findViewById(R.id.content_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        legislatorFragment = new legislatorFragment();
        transaction.add(R.id.id_content,legislatorFragment);
        billFragment = new billFragment();
        transaction.add(R.id.id_content,billFragment);
        committeeFragment = new committeeFragment();
        transaction.add(R.id.id_content,committeeFragment);
        favoriteFragment = new favoriteFragment();
        transaction.add(R.id.id_content,favoriteFragment);
        transaction.commit();
        useFragment(1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (legTabLayout ==null ||legViewPager== null){
        useTab(1);

        }
        if (billTabLayout == null || billViewPager == null){
        useTab(2);}
        if (comTabLayout == null || comViewPager == null){
        useTab(3);}
        if(favTabLayout == null || favViewPager == null){
        useTab(4);}
        JSONthread legJSONthread = new JSONthread(1,1, handler);
        legJSONthread.start();

        ladapter = new legListAdapter(this, favlegislator);
        badapter = new billListAdapter(this, favbill);
        cadapter = new comListAdapter(this, favcommittee);
        //listview
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_legislators) {
            useFragment(1);
            if (legJsonList == null){
                JSONthread legJSONthread = new JSONthread(1, handler);
                legJSONthread.start();
            }
            //useTab(1);
        } else if (id == R.id.nav_bills) {
            useFragment(2);
            if ( billJsonList == null){
                JSONthread billJSONthread = new JSONthread(2,1, handler);
                billJSONthread.start();
                billJSONthread = new JSONthread(2,2,handler);
                billJSONthread.start();
            }
            //useTab(2);
        } else if (id == R.id.nav_committees) {
            useFragment(3);
            if (comJsonList == null){
                JSONthread comJSONthread= new JSONthread(3,1, handler);
                comJSONthread.start();
                comJSONthread = new JSONthread(3,2,handler);
                comJSONthread.start();
                comJSONthread = new JSONthread(3,3,handler);
                comJSONthread.start();
            }
            //useTab(3);
        } else if (id == R.id.nav_favorites) {
            useFragment(4);

            favlegListView = (ListView) favview1.findViewById(R.id.favleglist);
            favbillListView = (ListView) favview2.findViewById(R.id.favbilllist);
            favcomListView = (ListView) favview3.findViewById(R.id.favcomlist);

            favlegListView.setAdapter(ladapter);
            favbillListView.setAdapter(badapter);
            favcomListView.setAdapter(cadapter);
            //useTab(4);
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void useFragment(int m){
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(legislatorFragment);
        transaction.hide(billFragment);
        transaction.hide(committeeFragment);
        transaction.hide(favoriteFragment);
        switch (m){
            case 1:
//                if (legislatorFragment ==null){
//                    legislatorFragment = new legislatorFragment();
//                }
                //transaction.replace(R.id.id_content,legislatorFragment);
                transaction.show(legislatorFragment);
                break;
            case 2:
//                if (billFragment == null){
//                    billFragment = new billFragment();
//                }
              //  transaction.replace(R.id.id_content,billFragment);
                transaction.show(billFragment);
                break;
            case 3:
//                if (committeeFragment == null){
//                    committeeFragment = new committeeFragment();
//                }
                //transaction.replace(R.id.id_content,committeeFragment);
                transaction.show(committeeFragment);
                break;
            case 4:
//                if (favoriteFragment == null){
//                    favoriteFragment = new favoriteFragment();
//                }
               // transaction.replace(R.id.id_content,favoriteFragment);
                transaction.show(favoriteFragment);
                break;
        }
        transaction.commit();
    }

    private void useTab(int m){

        if(m==1) {
            legViewPager = (ViewPager) legislatorFragment.getView().findViewById(legvp_view);
            legTabLayout = (TabLayout) legislatorFragment.getView().findViewById(legtabs);

            legViewPager.setOffscreenPageLimit(3);

            legInflater = LayoutInflater.from(this);
            legview1 = legInflater.inflate(R.layout.leg_bystate, null);
            legview2 = legInflater.inflate(R.layout.leg_house, null);
            legview3 = legInflater.inflate(R.layout.leg_senate, null);

            //添加页卡视图
            legViewList.add(legview1);
            legViewList.add(legview2);
            legViewList.add(legview3);


            //添加页卡标题
            legTitleList.add("BY STATE");
            legTitleList.add("HOUSE");
            legTitleList.add("SENATE");


            legTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
            legTabLayout.addTab(legTabLayout.newTab().setText(legTitleList.get(0)));//添加tab选项卡
            legTabLayout.addTab(legTabLayout.newTab().setText(legTitleList.get(1)));
            legTabLayout.addTab(legTabLayout.newTab().setText(legTitleList.get(2)));


            MyPagerAdapter legAdapter = new MyPagerAdapter(legViewList, legTitleList);
            legViewPager.setAdapter(legAdapter);//给ViewPager设置适配器
            legTabLayout.setupWithViewPager(legViewPager);//将TabLayout和ViewPager关联起来。
            legTabLayout.setTabsFromPagerAdapter(legAdapter);//给Tabs设置适配器
        }
        else if (m==2){
            billViewPager = (ViewPager) billFragment.getView().findViewById(billvp_view);
            billTabLayout = (TabLayout) billFragment.getView().findViewById(billtabs);

            billViewPager.setOffscreenPageLimit(2);

            billInflater = LayoutInflater.from(this);
            billview1 = billInflater.inflate(R.layout.active_bill, null);
            billview2 = billInflater.inflate(R.layout.new_bill, null);


            //添加页卡视图
            billViewList.add(billview1);
            billViewList.add(billview2);



            //添加页卡标题
            billTitleList.add("NEW BILL");
            billTitleList.add("ACTIVE BILL");



            billTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
            billTabLayout.addTab(billTabLayout.newTab().setText(billTitleList.get(0)));//添加tab选项卡
            billTabLayout.addTab(billTabLayout.newTab().setText(billTitleList.get(1)));



            MyPagerAdapter billAdapter = new MyPagerAdapter(billViewList, billTitleList);
            billViewPager.setAdapter(billAdapter);//给ViewPager设置适配器
            billTabLayout.setupWithViewPager(billViewPager);//将TabLayout和ViewPager关联起来。
            billTabLayout.setTabsFromPagerAdapter(billAdapter);//给Tabs设置适配器
        }
        else if (m==3){
            comViewPager = (ViewPager) committeeFragment.getView().findViewById(R.id.comvp_view);
            comTabLayout = (TabLayout) committeeFragment.getView().findViewById(R.id.comtabs);

            comViewPager.setOffscreenPageLimit(3);

            comInflater = LayoutInflater.from(this);
            comview1 = comInflater.inflate(R.layout.com_house, null);
            comview2 = comInflater.inflate(R.layout.com_senate, null);
            comview3 = comInflater.inflate(R.layout.com_joint, null);


            //添加页卡视图
            comViewList.add(comview1);
            comViewList.add(comview2);
            comViewList.add(comview3);



            //添加页卡标题
            comTitleList.add("HOUSE");
            comTitleList.add("SENATE");
            comTitleList.add("JOINT");


            comTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
            comTabLayout.addTab(comTabLayout.newTab().setText(comTitleList.get(0)));//添加tab选项卡
            comTabLayout.addTab(comTabLayout.newTab().setText(comTitleList.get(1)));
            comTabLayout.addTab(comTabLayout.newTab().setText(comTitleList.get(2)));



            MyPagerAdapter comAdapter = new MyPagerAdapter(comViewList, comTitleList);
            comViewPager.setAdapter(comAdapter);//给ViewPager设置适配器
            comTabLayout.setupWithViewPager(comViewPager);//将TabLayout和ViewPager关联起来。
            comTabLayout.setTabsFromPagerAdapter(comAdapter);//给Tabs设置适配器
        }
        else if (m==4){
            favViewPager = (ViewPager) favoriteFragment.getView().findViewById(R.id.favvp_view);
            favTabLayout = (TabLayout) favoriteFragment.getView().findViewById(R.id.favtabs);

            favViewPager.setOffscreenPageLimit(3);

            favInflater = LayoutInflater.from(this);
            favview1 = favInflater.inflate(R.layout.fav_leg, null);
            favview2 = favInflater.inflate(R.layout.fav_bill, null);
            favview3 = favInflater.inflate(R.layout.fav_com, null);


            //添加页卡视图
            favViewList.add(favview1);
            favViewList.add(favview2);
            favViewList.add(favview3);



            //添加页卡标题
            favTitleList.add("LEGISLATORS");
            favTitleList.add("BILLS");
            favTitleList.add("COMMITTEES");


            favTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
            favTabLayout.addTab(favTabLayout.newTab().setText(favTitleList.get(0)));//添加tab选项卡
            favTabLayout.addTab(favTabLayout.newTab().setText(favTitleList.get(1)));
            favTabLayout.addTab(favTabLayout.newTab().setText(favTitleList.get(2)));



            MyPagerAdapter favAdapter = new MyPagerAdapter(favViewList, favTitleList);
            favViewPager.setAdapter(favAdapter);//给ViewPager设置适配器
            favTabLayout.setupWithViewPager(favViewPager);//将TabLayout和ViewPager关联起来。
            favTabLayout.setTabsFromPagerAdapter(favAdapter);//给Tabs设置适配器
        }
    }

    //handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                   // Object obj = msg.obj;
                    legJsonList = (List<JSONObject>) msg.obj;
                    legListAdapter legListAdapter = new legListAdapter(getBaseContext(), legJsonList);
                    Log.i("JSON", "legJsonListnew = " + legJsonList );
                    //listview
                    legbystate_listView=(ListView) findViewById(legbystatelist);
                    legbystate_listView.setAdapter(new legListAdapter(getBaseContext(), legJsonList));//listview
                    leghouse_listView = (ListView) findViewById(leghouselist);
                    leghouse_listView.setAdapter(new legListAdapter(getBaseContext(),legJsonList));
                    legsenate_listView=(ListView) findViewById(legsenatelist);
                    legsenate_listView.setAdapter(new legListAdapter(getBaseContext(), legJsonList));//listview
                    Collections.sort(legJsonList, new Comparator<JSONObject>() {
                        @Override

                        public int compare(JSONObject legJsonlistItem1, JSONObject legJsonlistItem2 ) {
                            try {
                                return legJsonlistItem1.getString("state_name").compareTo(legJsonlistItem2.getString("state_name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return 0;
                            }
                        }
                    });
                    getIndexList(alphabet_letter);
                    displayIndex(1);
//                    displayIndex(2);

                    break;
                case 2:
                    billJsonList = (List<JSONObject>) msg.obj;
                    billListAdapter billListAdapter = new billListAdapter(getBaseContext(), billJsonList);
                    int billkind = msg.arg1;
                    if(billkind == 1){
                        //listview
                        billactive_listView=(ListView)findViewById(R.id.billactivelist);
                        billactive_listView.setAdapter(new billListAdapter(getBaseContext(), billJsonList));//listview
                    }else {
                        billnew_listView=(ListView)findViewById(R.id.billnewlist);
                        billnew_listView.setAdapter(new billListAdapter(getBaseContext(), billJsonList));
                    }

                    Log.i("JSON", "billJsonListnew = " + billJsonList );

                    break;
                case 3:
                    comJsonList = (List<JSONObject>) msg.obj;
                    comListAdapter comListAdapter = new comListAdapter(getBaseContext(), comJsonList);
                    Log.i("JSON", "comJsonListnew = " + comJsonList );
                    //listview
                    int comkind = msg.arg1;
                    if(comkind == 1){
                    comhouse_listView=(ListView)findViewById(R.id.comhouselist);
                    comhouse_listView.setAdapter(new comListAdapter(getBaseContext(), comJsonList));}
                    else if(comkind ==2){
                    comsenate_listView=(ListView)findViewById(R.id.comsenatelist);
                    comsenate_listView.setAdapter(new comListAdapter(getBaseContext(), comJsonList));}//listview
                    else if(comkind ==3){
                        comjoint_listView=(ListView)findViewById(R.id.comjointlist);
                        comjoint_listView.setAdapter(new comListAdapter(getBaseContext(), comJsonList));
                    }
                    break;
                case 4:
                    break;
            }

        }
    };
//handler

    private void getIndexList(String[] alphabet) {
        mapIndex = new LinkedHashMap<String, Integer>();

//        TextView SelectedAlphabet;
//        Map<String, Integer> mapIndex;
        alphabetMap = new HashMap<Integer, String>();
        for (int i = 0; i < alphabet.length; i++) {

            String alphaLetter = alphabet[i];

            int j = startWithAlphabetIndex(alphaLetter);
            if(j!=-1){
                mapIndex.put(alphabet[i], j);
                alphabetMap.put(j, alphabet[i]);
            }
            else{

                for(int k = i+1;k<alphabet.length;k++){
                    alphaLetter = alphabet[k];
                    j = startWithAlphabetIndex(alphaLetter);
                    if(j>0){
                        mapIndex.put(alphabet[i], j);
                        alphabetMap.put(j, alphabet[i-1]);
                        break;
                    }
                    break;
                }


            }

        }

    }
    public int startWithAlphabetIndex(String alphabet){
        int retValue = -1;
        for(int j =0; j < legJsonList.size(); j++){
            try {
                if(legJsonList.get(j).getString("state_name").startsWith(alphabet)){
                    retValue = j;
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return retValue;
    }
    private void displayIndex(int tabnumber) {

        TextView textView;
        switch (tabnumber){
            case 1:
                LinearLayout bystateindexLayout = (LinearLayout) findViewById(R.id.legbystate_side_index);
                List<String> indexList1 = new ArrayList<String>(mapIndex.keySet());
                for (String index : indexList1) {
                    textView = (TextView) getLayoutInflater().inflate(
                            R.layout.side_index, null);
                    textView.setText(index);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView selectedIndex = (TextView) view;
                            legbystate_listView.setSelection(mapIndex.get(selectedIndex.getText()));
                            // leghouse_listView.setSelection(mapIndex.get(selectedIndex.getText()));
                        }
                    });
                    bystateindexLayout.addView(textView);
                }
//            case 2:
//                LinearLayout houseindexLayout = (LinearLayout) findViewById(R.id.leghouse_side_index);
//                List<String> indexList2 = new ArrayList<String>(mapIndex.keySet());
//                for (String index : indexList2) {
//                    textView = (TextView) getLayoutInflater().inflate(
//                            R.layout.side_index, null);
//                    textView.setText(index);
//                    textView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            TextView selectedIndex = (TextView) view;
//                            //legbystate_listView.setSelection(mapIndex.get(selectedIndex.getText()));
//                             leghouse_listView.setSelection(mapIndex.get(selectedIndex.getText()));
//                        }
//                    });
//                    houseindexLayout.addView(textView);
//                }
        }
        //ScrollView scroll;

    }


}
