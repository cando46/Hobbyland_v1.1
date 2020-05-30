package com.hobbyland.version1.JoinEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hobbyland.version1.HelperClasses.EventHelperClass;
import com.hobbyland.version1.R;

import java.util.ArrayList;

public class JoinEventActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    DatabaseReference mRef;
    FirebaseRecyclerOptions<EventItem> options;
    FirebaseRecyclerAdapter<EventItem, EventViewHolder> adapter;
    DatabaseReference userEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }


    private void init() {
        recyclerView = findViewById(R.id.rv_join_event_events);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mRef= FirebaseDatabase.getInstance().getReference("Event").child("EventID");
        options = new FirebaseRecyclerOptions.Builder<EventItem>().setQuery(mRef, EventItem.class).build();

        adapter = new FirebaseRecyclerAdapter<EventItem, EventViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, final int i, @NonNull final EventItem eventItem) {

                boolean isExpanded = eventItem.isExpanded();
                eventViewHolder.expandable.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                eventViewHolder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventItem.setExpanded(!eventItem.isExpanded());
                        notifyItemChanged(i);
                    }
                });
                eventViewHolder.hobbyPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventItem.setExpanded(!eventItem.isExpanded());
                        notifyItemChanged(i);
                    }
                });

                userEvent=FirebaseDatabase.getInstance().getReference("Event").child("UserEvent").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                eventViewHolder.join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventHelperClass event= new EventHelperClass(eventItem.getTitle(),
                                eventItem.getHobbyName(),
                                eventItem.getDate(),
                                eventItem.getQuota(),
                                eventItem.getLocation(),
                                FirebaseAuth.getInstance().getCurrentUser().getUid());
                        userEvent.child(eventItem.getTitle()).setValue(event);
                        Toast.makeText(getApplicationContext(), "You have been joined "+eventItem.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
                eventViewHolder.details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                eventViewHolder.title.setText(eventItem.getTitle());
                eventViewHolder.location.setText("Location: "+eventItem.getLocation());
                eventViewHolder.quota.setText("Quota: "+eventItem.getQuota());
                eventViewHolder.date.setText("Date: "+eventItem.getDate());

                switch (eventItem.getHobbyName()){
                    case "Football":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.football);
                        break;
                    case "Basketball":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.basketball);
                        break;
                    case "Volleyball":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.volleyball);
                        break;
                    case "Tennis":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.tennis);
                        break;
                    case "Trekking":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.trekking);
                        break;
                    case "Running":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.runing);
                        break;
                    case "Table Tennis":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.table_tennis);
                        break;
                    case "Swimming":
                        eventViewHolder.hobbyPhoto.setImageResource(R.drawable.swimming);
                        break;

                }


            }

            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.join_event_item_layout, parent, false);
                return new EventViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }
}
