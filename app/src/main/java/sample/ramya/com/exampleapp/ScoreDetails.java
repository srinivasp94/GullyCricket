package sample.ramya.com.exampleapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;

import sample.ramya.com.exampleapp.database.Dbhandler;

public class ScoreDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String a_team;
    String b_team;
    Button endGame;
    Dbhandler dbhandler;
    int scoreA,scoreB;
    TeamAFragment aFragment;
    TeamBFragment bFragment;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_activity_score_details);

        dbhandler=new Dbhandler(this);
//         scoreA=dbhandler.getScoreA();
//         scoreB=dbhandler.getScoreB();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        dbhandler=new Dbhandler(this);

        endGame = (Button) toolbar.findViewById(R.id.btn_endgame);
        endGame.setVisibility(View.GONE);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActionendgame();
            }
        });

        Log.d("###teams", a_team + " && " + b_team);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreDetails.this, PlayerDataActivity.class);
                startActivity(intent);
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            case  R.id.action_endgame:
                callActionendgame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void callActionendgame() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(ScoreDetails.this);
        builder.create();
        builder.setTitle("Confirm End");
        builder.setMessage("Are you sure want to End Game...?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dbhandler.deleteTables();
                SharedPreferences preferences=getSharedPreferences("myPreferences",Context.MODE_PRIVATE);
//                preferences.getBoolean("GAMESTATUS",false);

                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(ScoreDetails.this,FlashScreenActivity.class));
                finish();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home,menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle b = new Bundle();
        SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

        a_team = preferences.getString("TEAMA", null);
        b_team = preferences.getString("TEAMB", null);
        Log.d("!!!!", a_team + "" + b_team);
        /*
        a_team = getIntent().getStringExtra("TEAM1");
        b_team = getIntent().getStringExtra("TEAM2");*/

        b.putString("TAB1", a_team );
        b.putString("TAB2", b_team);
        aFragment = new TeamAFragment();
        aFragment.setArguments(b);
        bFragment = new TeamBFragment();
        bFragment.setArguments(b);
        scoreA=dbhandler.getScoreA();
        scoreB=dbhandler.getScoreB();

        adapter.addFragment(aFragment, a_team + "  (" + scoreA + ")");
        adapter.addFragment(bFragment, b_team + "  (" + scoreB + ")");
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public  void setFragmentTitle(Fragment fragment,int position,String title)
        {
            mFragmentTitleList.set(position,title);
//            this.getPageTitle(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        scoreA=dbhandler.getScoreA();
        scoreB=dbhandler.getScoreB();

        adapter.setFragmentTitle(aFragment,0, a_team + "  (" + scoreA + ")");
        adapter.setFragmentTitle(bFragment,1,b_team + "  (" + scoreB + ")");
        adapter.notifyDataSetChanged();

    }
}
