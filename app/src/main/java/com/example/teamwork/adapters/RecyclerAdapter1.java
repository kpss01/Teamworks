package com.example.teamwork.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.teamwork.MySharedPreferences;
import com.example.teamwork.R;
import com.example.teamwork.item;

import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.MyViewHolder> {

    private Context mContext;
    private List<item> itemList;
    public item Item;
    MySharedPreferences mySharedPreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,album_id,id;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);

            title=view.findViewById(R.id.title);
            album_id=view.findViewById(R.id.album_id);
            id=view.findViewById(R.id.id);
            image=view.findViewById(R.id.image);

        }
    }


    public RecyclerAdapter1(Context mContext, List<item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
        mySharedPreferences=new MySharedPreferences(mContext);
    }

    @Override
    public RecyclerAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new RecyclerAdapter1.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter1.MyViewHolder holder, int position) {
        Item = itemList.get(position);

        holder.title.setText("Title: "+Item.getTitle());
        holder.album_id.setText("Album id: "+Item.getAlbum());
        holder.id.setText("ID: "+Item.getId());

        Glide.with(mContext).load(Item.getImage()) .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

