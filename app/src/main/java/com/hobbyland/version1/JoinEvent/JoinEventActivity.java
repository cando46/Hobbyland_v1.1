package com.hobbyland.version1.JoinEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.hobbyland.version1.R;

import java.util.ArrayList;

public class JoinEventActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<EventItem> events;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        init();
    }

    private void initData() {
        events= new ArrayList<>();
        events.add(new EventItem(1,R.drawable.basketball,"Corona Basketball","Date: 22.05.2020 18:00","0/10","Atatürk Basketball Stadium"));
        events.add(new EventItem(1,R.drawable.football,"Covid-19 Stress Atmaca","Date: 25.05.2020 21:00","0/12","Recep Halısaha Tesisleri"));
        events.add(new EventItem(1,R.drawable.tennis,"Event Come Come","Date: 28.05.2020 19:00","0/10","Bostanlı Sahil Tenis Kortları"));
    }

    private void init() {
        recyclerView=findViewById(R.id.rv_join_event_events);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        adapter=new EventAdapter(events);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
