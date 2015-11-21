package com.edocent.jobsmartlyapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.edocent.jobsmartlyapp.utility.AppConstants;

public class MainActivity extends Activity implements MainActivityFragment.NoInternetInterface{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        /*To add space between app icon and nav drawer icon*/
        ImageView imageView = (ImageView) findViewById(android.R.id.home);
        imageView.setPadding(300, 0, 0, 0);
        /*Ends*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
