package com.hobbyland.version1;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hobbyland.version1.CreateEvent.CreateEventActivity;
import com.hobbyland.version1.FindPartner.FindPartnerActivity;
import com.hobbyland.version1.Friends.FriendsActivity;
import com.hobbyland.version1.JoinEvent.JoinEventActivity;
import com.hobbyland.version1.Profile.ProfileOwnerActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;


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
    TextView username;
    TextView locationText;
    CircleImageView userProfile;
    ProgressDialog progressDialog;
    DatabaseReference mRef;
    String currentUserName ;
    String currentUserLocation;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        firebaseProcesses();
        return view;
    }

    private void firebaseProcesses() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        mRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentUserName = dataSnapshot.child("username").getValue(String.class);
                String state=dataSnapshot.child("LastKnownLocation").child("state").getValue(String.class);
                String country=dataSnapshot.child("LastKnownLocation").child("country").getValue(String.class);
                currentUserLocation=state+","+country;
                username.setText(currentUserName);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void init(View view) {

        username = view.findViewById(R.id.tv_home_username);
        findPartner = view.findViewById(R.id.cv_home_find_partner);
        friends = view.findViewById(R.id.cv_home_friends);
        createEvent = view.findViewById(R.id.cv_home_create_event);
        joinEvent = view.findViewById(R.id.cv_home_join_event);
        lookAround = view.findViewById(R.id.cv_home_look_around);
        settings = view.findViewById(R.id.cv_home_settings);
        userProfile = view.findViewById(R.id.cimv_home_profile_photo);
        locationText = view.findViewById(R.id.tv_home_location_info);
        //Set on click listener for card views
        findPartner.setOnClickListener(this);
        friends.setOnClickListener(this);
        createEvent.setOnClickListener(this);
        joinEvent.setOnClickListener(this);
        lookAround.setOnClickListener(this);
        settings.setOnClickListener(this);
        userProfile.setOnClickListener(this);

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
            case R.id.cimv_home_profile_photo:
                onClickProfilePhoto();
                break;
        }
    }

    private void onClickProfilePhoto() {
        Intent intent = new Intent(getActivity(), ProfileOwnerActivity.class);

        startActivity(intent);
    }

    private void onClickSettings() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);

    }

    private void onClickLookAround() {
        Toast.makeText(getContext(), "Look Around Clicked", Toast.LENGTH_LONG).show();
    }

    private void onClickJoinEvent() {
        Intent intent = new Intent(getActivity(), JoinEventActivity.class);
        startActivity(intent);

    }

    private void onClickCreateEvent() {
        Intent intent = new Intent(getActivity(), CreateEventActivity.class);
        startActivity(intent);
    }

    private void onClickFriends() {
        Intent intent = new Intent(getActivity(), FriendsActivity.class);
        startActivity(intent);

    }

    private void onClickFindPartner() {
        Intent intent = new Intent(getActivity(), FindPartnerActivity.class);
        startActivity(intent);
    }

}
