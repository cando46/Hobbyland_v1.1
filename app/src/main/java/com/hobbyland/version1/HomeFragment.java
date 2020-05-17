package com.hobbyland.version1;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    CardView findPartner;
    CardView friends;
    CardView createEvent;
    CardView joinEvent;
    CardView lookAround;
    CardView settings;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        findPartner = view.findViewById(R.id.cv_home_find_partner);
        friends = view.findViewById(R.id.cv_home_friends);
        createEvent = view.findViewById(R.id.cv_home_create_event);
        joinEvent = view.findViewById(R.id.cv_home_join_event);
        lookAround = view.findViewById(R.id.cv_home_look_around);
        settings = view.findViewById(R.id.cv_home_settings);
        //Set on click listener for card views
        findPartner.setOnClickListener(this);
        friends.setOnClickListener(this);
        createEvent.setOnClickListener(this);
        joinEvent.setOnClickListener(this);
        lookAround.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_home_find_partner:
                onClickFindPartner();
                break;
            case R.id.cv_home_friends:
                onClickFriends();
                break;
            case R.id.cv_home_create_event:
                onClickCreateEvent();
                break;
            case R.id.cv_home_join_event:
                onClickJoinEvent();
                break;
            case R.id.cv_home_look_around:
                onClickLookAround();
                break;
            case R.id.cv_home_settings:
                onClickSettings();
                break;
        }
    }

    private void onClickSettings() {
        Toast.makeText(getContext(),"Settings Clicked",Toast.LENGTH_LONG).show();

    }

    private void onClickLookAround() {
        Toast.makeText(getContext(),"Look Around Clicked",Toast.LENGTH_LONG).show();
    }

    private void onClickJoinEvent() {
        Toast.makeText(getContext(),"Join Event Clicked",Toast.LENGTH_LONG).show();

    }

    private void onClickCreateEvent() {
        Intent intent= new Intent(getActivity(),CreateEventActivity.class);
        startActivity(intent);
    }

    private void onClickFriends() {
        Intent intent= new Intent(getActivity(),FriendsActivity.class);
        startActivity(intent);

    }

    private void onClickFindPartner() {
        Intent intent= new Intent(getActivity(),FindPartnerActivity.class);
        startActivity(intent);
    }
}
