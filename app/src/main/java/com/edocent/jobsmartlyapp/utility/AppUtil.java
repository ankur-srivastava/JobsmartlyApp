package com.edocent.jobsmartlyapp.utility;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ankur on 11/21/15.
 */
public class AppUtil {

    public static void checkConnectivity(ConnectivityManager connMgr){
        //Check if user is online
        boolean isOnline = isOnline(connMgr);
        if(isOnline){
            checkWiFiConnectivity(connMgr);
            checkMobileConnectivity(connMgr);
        }else{
            //Display a message or a fragment
        }
    }

    public static boolean checkWiFiConnectivity(ConnectivityManager connMgr){
        return connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

    public static boolean checkMobileConnectivity(ConnectivityManager connMgr){
        return connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
    }

    public static boolean isOnline(ConnectivityManager connMgr) {
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
