package com.example.moneytransferadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PasscodeActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String passcode = "passcodeKey";
    public static final boolean passCodeLoggedIn = false;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnDone;
    Button btnDel;
    ImageButton btnfirst;
    ImageButton btnsecond;
    ImageButton btnThird;
    ImageButton btnImageFourth;
    String pass="";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        btn1=(Button) findViewById(R.id.btn1);
        btn0=(Button) findViewById(R.id.btn0);
        btn2=(Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        btn4=(Button) findViewById(R.id.btn4);
        btn5=(Button) findViewById(R.id.btn5);
        btn6=(Button) findViewById(R.id.btn6);
        btn7=(Button) findViewById(R.id.btn7);
        btn8=(Button) findViewById(R.id.btn8);
        btn9=(Button) findViewById(R.id.btn9);
        btnDel=(Button) findViewById(R.id.btnDel);
        btnDone=(Button) findViewById(R.id.btnDone);
        btnfirst=(ImageButton) findViewById(R.id.btnfirstPassInput);
        btnsecond=(ImageButton) findViewById(R.id.btnSecondPassInput);
        btnThird=(ImageButton) findViewById(R.id.btnThirdPassInput);
        btnImageFourth=(ImageButton) findViewById(R.id.btnFourthPassInput);
    }
    public void btn1Clicked(View view)
    {
        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"1";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"1";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"1";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"1";
        }
    }
    public void btn0Clicked(View view)
    {

        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"0";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"0";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"0";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"0";
        }

    }
    public void btn2Clicked(View view)
    {

        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"2";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"2";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"2";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"2";
        }

    }
    public void btn3Clicked(View view)
    {


        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"3";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"3";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"3";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"3";
        }

    }
    public void btn4Clicked(View view)
    {

        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"4";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"4";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"4";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"4";
        }

    }
    public void btn5Clicked(View view)
    {

        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"5";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"5";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"5";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"5";
        }
    }
    public void btn6Clicked(View view)
    {

        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"6";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"6";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"6";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"6";
        }
    }
    public void btn7Clicked(View view)
    {

        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"7";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"7";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"7";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"7";
        }

    }
    public void btn8Clicked(View view)
    {


        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"8";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"8";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"8";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"8";
        }

    }
    public void btn9Clicked(View view)
    {

        if(pass.length()<1)
        {
            btnfirst.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"9";
        }
        else if(pass.length()<2)
        {
            btnsecond.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"9";
        }
        else if(pass.length()<3)
        {
            btnThird.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"9";
        }
        else if (pass.length()<4)
        {
            btnImageFourth.setBackgroundResource(R.drawable.filled_circle);
            pass=pass+"9";
        }
    }
    public void btnDoneClicked(View view)
    {
        if(pass.equals(sharedpreferences.getString(passcode, "")))
        {
            SharedPreferences.Editor edit = sharedpreferences.edit();
            edit.putBoolean("passCodeLoggedIn", true);
            edit.commit(); //apply
            pass="";
            btnfirst.setBackgroundResource(R.drawable.empty_circle);
            btnsecond.setBackgroundResource(R.drawable.empty_circle);
            btnThird.setBackgroundResource(R.drawable.empty_circle);
            btnImageFourth.setBackgroundResource(R.drawable.empty_circle);
            Intent intent=new Intent(PasscodeActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Incorrect Passcode", Toast.LENGTH_SHORT).show();
            pass="";
            btnfirst.setBackgroundResource(R.drawable.empty_circle);
            btnsecond.setBackgroundResource(R.drawable.empty_circle);
            btnThird.setBackgroundResource(R.drawable.empty_circle);
            btnImageFourth.setBackgroundResource(R.drawable.empty_circle);
        }

    }
    public void btnDelClicked(View view)
    {
        if (pass != null && pass.length() > 0) {
            pass = pass.substring(0, pass.length() - 1);

            if(pass.length() == 3){
                btnImageFourth.setBackgroundResource(R.drawable.empty_circle);
            }
            if(pass.length() == 2){
                btnThird.setBackgroundResource(R.drawable.empty_circle);
            }
            if(pass.length() == 1){
                btnsecond.setBackgroundResource(R.drawable.empty_circle);
            }
            if(pass.length() == 0){
                btnfirst.setBackgroundResource(R.drawable.empty_circle);
            }
        }
    }
}
