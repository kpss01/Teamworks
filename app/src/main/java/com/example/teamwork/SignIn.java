package com.example.teamwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

public class SignIn extends AppCompatActivity {
    LinearLayout linearLayout,linearLayout_1;
    EditText username,password;
    Button signIn,not_account;

    int width= Resources.getSystem().getDisplayMetrics().widthPixels;
    int height= Resources.getSystem().getDisplayMetrics().heightPixels;

    MySharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        linearLayout=findViewById(R.id.linear_layout);
        linearLayout_1=findViewById(R.id.linear_layout_1);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,(int)(height*0.1),20,0);
        linearLayout.setLayoutParams(params);

        LinearLayout.LayoutParams params_1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_1.setMargins((int)(width*0.08),(int)(height*0.1),(int)(width*0.08),0);
        linearLayout_1.setLayoutParams(params_1);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        sharedPreferences=new MySharedPreferences(this);

        signIn=findViewById(R.id.login);
        RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0,(int)(height-(height*0.1)),0,0);
        params1.width=width;
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidEmail(username.getText().toString())) {
                    if (password.getText().toString().length() > 0) {
                        if (sharedPreferences.get(username.getText().toString() + "," + password.getText().toString()) == null) {
                            new AlertDialog.Builder(SignIn.this).setMessage("This account is not registered").setTitle("Error")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            ;
                                        }
                                    }).show();
                        } else {
                            sharedPreferences.set("user", username.getText().toString() + "," + password.getText().toString());
                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    } else {
                        new AlertDialog.Builder(SignIn.this).setMessage("Please enter valid username and password").setTitle("Error")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        ;
                                    }
                                }).show();
                    }
                }
                else{
                    new AlertDialog.Builder(SignIn.this).setMessage("Please enter valid email").setTitle("Error")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    ;
                                }
                            }).show();
                }
            }
        });

        not_account=findViewById(R.id.not_account);
        not_account.setLayoutParams(params1);
        not_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}