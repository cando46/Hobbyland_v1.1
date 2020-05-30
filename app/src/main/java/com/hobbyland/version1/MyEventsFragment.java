package com.hobbyland.version1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hobbyland.version1.JoinEvent.EventItem;
import com.hobbyland.version1.JoinEvent.EventViewHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends Fragment {

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    DatabaseReference mRef;
    FirebaseRecyclerOptions<EventItem> options;
    FirebaseRecyclerAdapter<EventItem, EventViewHolder> adapter;


    public MyEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_events, container, false);
        return view;
    }

}
