package com.example.moneytransferadmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends Activity {
    TextView whiteBg;
    ImageView errImg;
    public static final String url = "http://192.168.31.134:81/MoneyTransfer/admin/login/login.php";
    //String url = "http://192.168.1.111:81/MoneyTransfer/admin/login/login.php";

    // variable para manejar el navegador empotrado
    WebView mainWebView;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String passcode = "passcodeKey";
    public static boolean connected;
    boolean firstAccessNoInternet = false;

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
        mainWebView.loadUrl(url);
//        if(isConnectedToServer(url, 20 * 1000)){
//            mainWebView.loadUrl(url);
//        } else {
//            whiteBg.setVisibility(View.VISIBLE);
//            errImg.setVisibility(View.VISIBLE);
//        }
        new MyTask().execute(url);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Log.d("Connection 3", "on create");
                if(!connected) {
                    whiteBg.setVisibility(View.VISIBLE);
                    errImg.setVisibility(View.VISIBLE);
                    firstAccessNoInternet = true;
                }
            }
        }, 1000);

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
//        whiteBg.setVisibility(View.GONE);
//        errImg.setVisibility(View.GONE);

//        whiteBg.setVisibility(View.VISIBLE);
        new MyTask().execute(url);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Log.d("Connection 3", "on resume");

                if(!connected) {
                    whiteBg.setVisibility(View.VISIBLE);
                    errImg.setVisibility(View.VISIBLE);
                } else {
                    if(firstAccessNoInternet){
                        mainWebView.loadUrl(url);
                        firstAccessNoInternet = false;
                    }
                    whiteBg.setVisibility(View.GONE);
                    errImg.setVisibility(View.GONE);
                }
            }
        }, 1500);

    }

    private class MyWebViewClient extends WebViewClient {

        // permite la navegacion dentro del webview
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyTask extends AsyncTask<String, Void, Void> {
        private Exception exception;
        @Override
        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                if(isURLReachable(getApplicationContext(), url.toString())){
                    connected = true;
                    Log.d("Connection 1", String.valueOf(connected));
                } else {
                    connected = false;
                    Log.d("Connection 1", String.valueOf(connected));
                }
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // do something with result
        }

        public boolean isURLReachable(Context context, String tokenUrl) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL(tokenUrl);
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(1000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        Log.d("Connection", "Success !");
                        return true;
                    } else {
                        return false;
                    }
                } catch (MalformedURLException e1) {
                    Log.d("Connection", "Failed! " + e1.toString());
                    return false;
                } catch (IOException e) {
                    Log.d("Connection", "Failed! " + e.toString());
                    return false;
                }
            }
            return false;
        }
    }
}