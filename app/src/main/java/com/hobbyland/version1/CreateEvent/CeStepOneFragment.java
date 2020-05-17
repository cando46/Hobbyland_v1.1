package com.hobbyland.version1.CreateEvent;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hobbyland.version1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CeStepOneFragment extends Fragment implements View.OnClickListener {

    private CeStepOneListener listener;
    CardView football;
    CardView basketball;
    CardView volleyball;
    CardView tennis;
    CardView tableTennis;
    CardView swimming;
    CardView running;
    CardView trekking;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cv_ce_football:
                onClickFootball();
                break;
            case R.id.cv_ce_basketball:
                onClickBasketball();
                break;
            case R.id.cv_ce_volleyball:
                onClickVolleyball();
                break;
            case R.id.cv_ce_tennis:
                onClickTennis();
                break;
            case R.id.cv_ce_trekking:
                onClickTrekking();
                break;
            case R.id.cv_ce_table_tennis:
                onClickTableTennis();
                break;
            case R.id.cv_ce_running:
                onClickRunning();
                break;
            case R.id.cv_ce_swimming:
                onClickSwimming();
                break;
        }

    }


    public interface CeStepOneListener{

        void inputCeStepOneSent(int hobbyID);

    }
    public CeStepOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ce_step_one, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        football=view.findViewById(R.id.cv_ce_football);
        basketball=view.findViewById(R.id.cv_ce_basketball);
        tennis=view.findViewById(R.id.cv_ce_tennis);
        tableTennis=view.findViewById(R.id.cv_ce_table_tennis);
        trekking=view.findViewById(R.id.cv_ce_trekking);
        swimming=view.findViewById(R.id.cv_ce_swimming);
        running=view.findViewById(R.id.cv_ce_running);
        volleyball=view.findViewById(R.id.cv_ce_volleyball);
        //set on click listeners
        football.setOnClickListener(this);
        basketball.setOnClickListener(this);
        tennis.setOnClickListener(this);
        tableTennis.setOnClickListener(this);
        trekking.setOnClickListener(this);
        swimming.setOnClickListener(this);
        running.setOnClickListener(this);
        volleyball.setOnClickListener(this);
    }



    /*HOBBY ID'S
     * FOOTBALL=1
     * BASKETBALL=2
     * VOLLEYBALL=3
     * TENNIS=4
     * TREKKING=5
     * RUNNING=6
     * TABLE_TENNIS=7
     * SWIMMING=8
     * */

    private void onClickSwimming() {
        listener.inputCeStepOneSent(8);

    }

    private void onClickRunning() {
        listener.inputCeStepOneSent(6);

    }

    private void onClickTableTennis() {
        listener.inputCeStepOneSent(7);
    }

    private void onClickTrekking() {
        listener.inputCeStepOneSent(5);

    }

    private void onClickTennis() {
        listener.inputCeStepOneSent(4);

    }

    private void onClickVolleyball() {
        listener.inputCeStepOneSent(3);

    }

    private void onClickBasketball() {
        listener.inputCeStepOneSent(2);

    }

    private void onClickFootball() {
        listener.inputCeStepOneSent(1);

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof CeStepOneListener){
            listener=(CeStepOneListener) context;
        }else {
            throw new RuntimeException(context.toString()+"must implement FragmentStepOneListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}
