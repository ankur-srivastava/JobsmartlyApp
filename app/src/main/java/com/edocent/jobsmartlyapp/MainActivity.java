package com.edocent.jobsmartlyapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.edocent.jobsmartlyapp.utility.AppConstants;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.NoInternetInterface{

    private static final String TAG = MainActivity.class.getSimpleName();

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callNoInternetFragment() {
        NoInternetFragment noInternetFragment = new NoInternetFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.noInternetId, noInternetFragment);
        fragmentTransaction.commit();
    }

    public void exploreCategories(){
        startWebAppActivity(AppConstants.EXPLORE_CAREGORIES_URL);
    }

    public void manage(){
        startWebAppActivity(AppConstants.MANAGE_URL);
    }

    public void startWebAppActivity(String URL){
        Intent intent = new Intent(this, StartWebAppActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(AppConstants.START_ACTIVITY_KEY, URL);
        startActivity(intent);
        finish();
        //System.exit(0);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MainActivityFragment.customKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }
}
