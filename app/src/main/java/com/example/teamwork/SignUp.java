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

public class SignUp extends AppCompatActivity {
    LinearLayout linearLayout,linearLayout_1;
    EditText username,password,name,confirmPassword;
    Button signIn,not_account;

    int width= Resources.getSystem().getDisplayMetrics().widthPixels;
    int height= Resources.getSystem().getDisplayMetrics().heightPixels;

    MySharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        linearLayout=findViewById(R.id.linear_layout);
        linearLayout_1=findViewById(R.id.linear_layout_1);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,(int)(height*0.1),20,0);
        linearLayout.setLayoutParams(params);

        LinearLayout.LayoutParams params_1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_1.setMargins((int)(width*0.08),(int)(height*0.1),(int)(width*0.08),0);
        linearLayout_1.setLayoutParams(params_1);

        sharedPreferences=new MySharedPreferences(this);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        confirmPassword=findViewById(R.id.confirm_password);


        signIn=findViewById(R.id.login);
        RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0,(int)(height-(height*0.1)),0,0);
        params1.width=width;
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidEmail(username.getText().toString())) {
                    if (password.getText().toString().length() > 0 && name.getText().toString().length() > 0 && confirmPassword.getText().toString().length() > 0) {
                        if (confirmPassword.getText().toString().compareTo(password.getText().toString()) == 0) {

                            if (sharedPreferences.getBoolean(username.getText().toString())) {
                                new AlertDialog.Builder(SignUp.this).setMessage("This username is already registered").setTitle("Error")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                ;
                                            }
                                        }).show();
                            } else {
                                sharedPreferences.set(username.getText().toString() + "," + password.getText().toString(), name.getText().toString());
                                sharedPreferences.setBoolean(username.getText().toString(), true);
                                //sharedPreferences.setBoolean(username.getText().toString()+","+password.getText().toString(),true);
                                sharedPreferences.set("user", username.getText().toString() + "," + password.getText().toString());
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        } else {
                            new AlertDialog.Builder(SignUp.this).setMessage("Password and confirm password field didn't match").setTitle("Error")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            ;
                                        }
                                    }).show();
                        }
                    } else {
                        new AlertDialog.Builder(SignUp.this).setMessage("Please fill up ALL fields.").setTitle("Error")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        ;
                                    }
                                }).show();
                    }
                }
                else {
                    new AlertDialog.Builder(SignUp.this).setMessage("Please enter valid email.").setTitle("Error")
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