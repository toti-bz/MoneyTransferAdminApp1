package com.example.moneytransferadmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
    Handler handler;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String passcode = "passcodeKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                boolean preferenceContained = sharedpreferences.contains(passcode);
                if(preferenceContained == true){
//                    intent=new Intent(SplashScreen.this,PasscodeActivity.class);
                    intent=new Intent(SplashScreen.this,MainActivity.class);
                } else {
                    intent=new Intent(SplashScreen.this,setPassActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },3000);

    }
}

