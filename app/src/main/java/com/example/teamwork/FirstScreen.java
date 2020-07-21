package com.example.teamwork;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstScreen extends AppCompatActivity {


    int width=Resources.getSystem().getDisplayMetrics().widthPixels;
    int height= Resources.getSystem().getDisplayMetrics().heightPixels;

    Button signIn,signUp;

    MySharedPreferences sharedPreferences;

    TextView welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences=new MySharedPreferences(this);
        //sharedPreferences.clear();
        if(sharedPreferences.get("user")==null) {
            setContentView(R.layout.activity_first_screen);

            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

            welcomeText = findViewById(R.id.welcome_text);
            RelativeLayout.LayoutParams welcomeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            welcomeParams.setMargins(10, (int) (height * 0.2), 10, 0);
            welcomeText.setLayoutParams(welcomeParams);
            welcomeText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            System.out.println("Height and width===" + height + "====" + width);
            welcomeText.setTextSize((float) (width * 0.04));

            signIn = findViewById(R.id.sign_in);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("SignIn====");
                    Intent intent = new Intent(FirstScreen.this, SignIn.class);
                    startActivity(intent);
                }
            });

            signUp = findViewById(R.id.sign_up);
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("SignUP===");
                    Intent intent = new Intent(FirstScreen.this, SignUp.class);
                    startActivity(intent);
                }
            });
        }
        else{
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}