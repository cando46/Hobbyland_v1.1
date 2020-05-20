package com.hobbyland.version1.JoinEvent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hobbyland.version1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    ArrayList<EventItem> eventItems;

    public EventAdapter(ArrayList<EventItem> eventList) {

        eventItems = eventList;

    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.join_event_item_layout, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventItem current = eventItems.get(position);

        holder.hobbyImage.setImageResource(current.getImageResourceId());
        holder.date.setText(current.getDate());
        holder.quota.setText(current.getQuota());
        holder.location.setText(current.getLocation());
        holder.title.setText(current.getName());

        boolean isExpanded = eventItems.get(position).isExpanded();
        holder.expandable.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return eventItems.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView hobbyImage;
        TextView title;
        TextView date;
        TextView quota;
        TextView location;
        Button details;
        Button join;
        ConstraintLayout expandable;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            hobbyImage = itemView.findViewById(R.id.imv_join_item_hobby);
            title = itemView.findViewById(R.id.tv_join_item_hobby_name);
            date = itemView.findViewById(R.id.tv_join_item_date);
            quota = itemView.findViewById(R.id.tv_join_item_quota);
            location = itemView.findViewById(R.id.tv_join_item_location);
            details = itemView.findViewById(R.id.btn_join_item_details);
            join = itemView.findViewById(R.id.btn_join_item_join);
            expandable = itemView.findViewById(R.id.cons_layout_join_expandable);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventItem eventItem = eventItems.get(getAdapterPosition());
                    eventItem.setExpanded(!eventItem.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}
