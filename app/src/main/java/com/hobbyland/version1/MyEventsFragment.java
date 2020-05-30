package com.hobbyland.version1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
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
    FirebaseRecyclerAdapter<EventItem, myEventsViewHolder> adapter;


    public MyEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_events, container, false);
        recyclerView=view.findViewById(R.id.rv_my_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRef= FirebaseDatabase.getInstance().getReference("Event").child("UserEvent").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        options =
                new FirebaseRecyclerOptions.Builder<EventItem>()
                .setQuery(mRef,EventItem.class)
                .build();

        adapter= new FirebaseRecyclerAdapter<EventItem, myEventsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myEventsViewHolder myEventsViewHolder, final int i, @NonNull final EventItem eventItem) {

                myEventsViewHolder.join.setText("Leave");
                myEventsViewHolder.join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Are you sure to leave from '"+eventItem.getTitle()+"'")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mRef.child(eventItem.getTitle()).removeValue();
                                        Toast.makeText(getContext(), "You have been leaved from '"+eventItem.getTitle()+"'", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();


                    }
                });

                boolean isExpanded = eventItem.isExpanded();
                myEventsViewHolder.expandable.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                myEventsViewHolder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventItem.setExpanded(!eventItem.isExpanded());
                        notifyItemChanged(i);
                    }
                });
                myEventsViewHolder.hobbyPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventItem.setExpanded(!eventItem.isExpanded());
                        notifyItemChanged(i);
                    }
                });

                myEventsViewHolder.title.setText(eventItem.getTitle());
                myEventsViewHolder.location.setText("Location: "+eventItem.getLocation());
                myEventsViewHolder.quota.setText("Quota: "+eventItem.getQuota());
                myEventsViewHolder.date.setText("Date: "+eventItem.getDate());

                switch (eventItem.getHobbyName()){
                    case "Football":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.football);
                        break;
                    case "Basketball":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.basketball);
                        break;
                    case "Volleyball":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.volleyball);
                        break;
                    case "Tennis":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.tennis);
                        break;
                    case "Trekking":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.trekking);
                        break;
                    case "Running":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.runing);
                        break;
                    case "Table Tennis":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.table_tennis);
                        break;
                    case "Swimming":
                        myEventsViewHolder.hobbyPhoto.setImageResource(R.drawable.swimming);
                        break;

                }

            }

            @NonNull
            @Override
            public myEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.join_event_item_layout,parent,false);

                return new myEventsViewHolder(view);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    public static class myEventsViewHolder extends RecyclerView.ViewHolder{

        ImageView hobbyPhoto;
        TextView title;
        TextView date;
        TextView quota;
        TextView location;
        Button join;
        Button details;
        ConstraintLayout expandable;
        public myEventsViewHolder(@NonNull View itemView) {
            super(itemView);
            hobbyPhoto = itemView.findViewById(R.id.imv_join_item_hobby);
            title = itemView.findViewById(R.id.tv_join_item_hobby_name);
            date = itemView.findViewById(R.id.tv_join_item_date);
            quota = itemView.findViewById(R.id.tv_join_item_quota);
            location = itemView.findViewById(R.id.tv_join_item_location);
            join = itemView.findViewById(R.id.btn_join_item_join);
            details = itemView.findViewById(R.id.btn_join_item_details);
            expandable=itemView.findViewById(R.id.cons_layout_join_expandable);
        }
    }
}
