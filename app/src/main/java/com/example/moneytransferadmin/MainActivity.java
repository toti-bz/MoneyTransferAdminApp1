package com.example.moneytransferadmin;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {
    TextView whiteBg;
    ImageView errImg;

    // variable para manejar el navegador empotrado
    WebView mainWebView;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String passcode = "passcodeKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m =
                        StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setContentView(R.layout.activity_main);

        whiteBg = (TextView) findViewById(R.id.whiteBg);
        errImg = (ImageView) findViewById(R.id.errImg);
        whiteBg.setVisibility(View.GONE);
        errImg.setVisibility(View.GONE);

        if(isNetworkAvailable(getApplication()) == false){
            whiteBg.setVisibility(View.VISIBLE);
            errImg.setVisibility(View.VISIBLE);
        }

        // instanciamos el webview
        mainWebView = findViewById(R.id.webView);
        mainWebView.setWebViewClient(new MyWebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                boolean overrideUrlLoading = false;
                if (url != null && url.startsWith("whatsapp://")) {
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    overrideUrlLoading = true;
                } else {
                    view.loadUrl(url);
                }
                return overrideUrlLoading;
            }
        });
        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.getSettings().setDomStorageEnabled(true);
        mainWebView.getSettings().setAllowFileAccess(true);
        mainWebView.getSettings().setAllowContentAccess(true);
        mainWebView.getSettings().setLoadWithOverviewMode(true);

        // cargamos la pagina
        mainWebView.loadUrl("http://192.168.31.169:81/MT/MoneyTransfer/customer/forms/index.php");
    }

    private Boolean isNetworkAvailable(Application application) {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnected();
        }
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { permission },
                    requestCode);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mainWebView.canGoBack()) {
                        mainWebView.goBack();
                    } else {
//                        finish();
                        if (doubleBackToExitPressedOnce) {
                            Intent a = new Intent(Intent.ACTION_MAIN);
                            a.addCategory(Intent.CATEGORY_HOME);
                            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(a);
                        }

                        this.doubleBackToExitPressedOnce = true;
                        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                doubleBackToExitPressedOnce=false;
                            }
                        }, 2000);
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume(){
        super.onResume();
        mainWebView.loadUrl("http://192.168.31.169:81/MT/MoneyTransfer/admin/login/login.php");
        whiteBg.setVisibility(View.GONE);
        errImg.setVisibility(View.GONE);

        if(isNetworkAvailable(getApplication()) == false){
            whiteBg.setVisibility(View.VISIBLE);
            errImg.setVisibility(View.VISIBLE);
        }
    }

    private class MyWebViewClient extends WebViewClient {

        // permite la navegacion dentro del webview
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}