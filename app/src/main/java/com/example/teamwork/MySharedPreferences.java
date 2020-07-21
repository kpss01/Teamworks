package com.example.teamwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MySharedPreferences {


        public static final String MY_PREF = "MyPreferences";

        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        public MySharedPreferences(Context context) {
            this.sharedPreferences = context.getSharedPreferences(MY_PREF, 0);
            this.editor = this.sharedPreferences.edit();
        }

        public  void set(String key, String value) {
            this.editor.putString(key, value);
            this.editor.commit();
        }

        public void setBoolean(String key,boolean value){
            this.editor.putBoolean(key,value);
            this.editor.commit();
        }

        public void setlong(String key,long value){
            this.editor.putLong(key,value);
            this.editor.commit();
        }

        public String get(String key) {
            return this.sharedPreferences.getString(key, null);
        }

        public boolean getBoolean(String key){
            return this.sharedPreferences.getBoolean(key,false);
        }

        public long getLong(String key){
            return  this.sharedPreferences.getLong(key,0);
        }

        public void clear(String key) {
            this.editor.remove(key);
            this.editor.commit();
        }

        public void clear() {
            this.editor.clear();
            this.editor.commit();
        }



    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public  static String getValueofThem(Context context){
        SharedPreferences settings = context.getSharedPreferences("SetThem", Context.MODE_PRIVATE);
        String user_name= settings.getString("Them", "");
        return user_name;
    }


}
