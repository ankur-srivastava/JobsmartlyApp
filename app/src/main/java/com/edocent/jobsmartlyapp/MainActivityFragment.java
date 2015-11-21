package com.edocent.jobsmartlyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.edocent.jobsmartlyapp.utility.AppConstants;
import com.edocent.jobsmartlyapp.utility.AppUtil;

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    ConnectivityManager connMgr;
    static WebView myWebView;
    Button exploreCategories;
    Button more;
    NoInternetInterface mNoInternetInterface;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final Activity currentActivity = getActivity();

        exploreCategories = (Button) view.findViewById(R.id.exploreCategoriesId);
        more = (Button) view.findViewById(R.id.manageId);

        connMgr = (ConnectivityManager)currentActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isOnline = AppUtil.isOnline(connMgr);

        if(isOnline){
            exploreCategories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mNoInternetInterface != null){
                        mNoInternetInterface.exploreCategories();
                    }
                }
            });

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mNoInternetInterface != null){
                        mNoInternetInterface.manage();
                    }
                }
            });

            final ProgressDialog pd = ProgressDialog.show(currentActivity, "", "Please wait !!", true);

            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.setMax(100);
            pd.setProgress(100);

            myWebView = (WebView) view.findViewById(R.id.webview);
            myWebView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon){
                    pd.show();
                    closeDialog(pd);
                }
                @Override
                public void onPageFinished(WebView view, String url) {
                    if(pd!= null && pd.isShowing()) {
                        pd.dismiss();
                    }
                }
            });
            myWebView.loadUrl(AppConstants.MOBILE_CONTENT_URL);
            myWebView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }else{
            Log.v(TAG, "No Internet !!");

            exploreCategories.setVisibility(View.INVISIBLE);
            more.setVisibility(View.INVISIBLE);

            if(mNoInternetInterface != null){
                mNoInternetInterface.callNoInternetFragment();
            }
        }

        /*To add space between app icon and nav drawer icon*/
        //ImageView imageView = (ImageView) view.findViewById(android.R.id.home);
        //imageView.setPadding(300, 0, 0, 0);
        /*Ends*/

        return view;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mNoInternetInterface = (NoInternetInterface) activity;
    }

    public interface NoInternetInterface{
        void callNoInternetFragment();

        public void exploreCategories();

        public void manage();

        void startWebAppActivity(String URL);
    }

    private void closeDialog(final ProgressDialog pd) {
        Runnable progressThread = new Runnable() {
            @Override
            public void run() {
                pd.dismiss();
            }
        };

        Handler progressHandler = new Handler();
        progressHandler.postDelayed(progressThread, AppConstants.PROGRESS_DIALOG_TIME);
    }

    public static void customKeyDown(int keyCode, KeyEvent event) {
        if(myWebView != null){
            if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
                myWebView.goBack();
            }
        }
    }
}
