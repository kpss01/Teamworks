package com.example.teamwork.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.teamwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabOneFragment extends Fragment {

    Button email,mobile;

    public TabOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment_one, container, false);

        email=view.findViewById(R.id.email);
        mobile=view.findViewById(R.id.mobile);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW)
                            .setType("plain/text")
                            .setData(Uri.parse("abcde@gmail.com"))
                            .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    startActivity(intent);
                }catch (Exception e){
                    new AlertDialog.Builder(getActivity()).setMessage("Some error occur when trying to open GMAIL").setTitle("Error")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+91 9123456789"));
                startActivity(callIntent);
            }
        });
        return view;

    }

}
