package com.example.teamwork.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamwork.AsyncClient;
import com.example.teamwork.R;
import com.example.teamwork.adapters.RecyclerAdapter1;
import com.example.teamwork.item;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabThreeFragment extends Fragment {


    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<item> arrayList;
    RecyclerAdapter1 recyclerAdapter1;
    public TabThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment_three, container, false);

        recyclerView=view.findViewById(R.id.recycler);
        progressBar=view.findViewById(R.id.progress_bar);

        arrayList=new ArrayList<item>();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());

        recyclerAdapter1=new RecyclerAdapter1(getActivity(),arrayList);
        recyclerView.setAdapter(recyclerAdapter1);
        recyclerView.setLayoutManager(linearLayoutManager);


        AsyncClient.get("http://jsonplaceholder.typicode.com/photos",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println("Response=="+response);

                for(int i=0;i<response.length();i++){

                    item Item=new item();
                    try {
                        JSONObject object=response.getJSONObject(i);
                        Item.setAlbum(object.getString("albumId"));
                        Item.setId(object.getString("id"));
                        Item.setImage("https://t8f8b3g9.stackpathcdn.com/wp-content/uploads/2020/04/Why-team-work-matter-most-in-project-management.png");
                        Item.setTitle(object.getString("title"));
                        arrayList.add(Item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerAdapter1.notifyDataSetChanged();
                recyclerView.invalidate();
                recyclerView.refreshDrawableState();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });


        return view;
    }

}
