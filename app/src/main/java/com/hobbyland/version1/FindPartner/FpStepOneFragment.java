package com.hobbyland.version1.FindPartner;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hobbyland.version1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FpStepOneFragment extends Fragment implements View.OnClickListener {


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
    private FragmentStepOneListener listener;
    CardView football;
    CardView basketball;
    CardView volleyball;
    CardView tennis;
    CardView tableTennis;
    CardView swimming;
    CardView running;
    CardView trekking;
    public FpStepOneFragment() {
        // Required empty public constructor
    }

    public interface FragmentStepOneListener{

        void inputStepOneSent(int hobbyID);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fp_step_one, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        football=view.findViewById(R.id.cv_fp_football);
        basketball=view.findViewById(R.id.cv_fp_basketball);
        tennis=view.findViewById(R.id.cv_fp_tennis);
        tableTennis=view.findViewById(R.id.cv_fp_table_tennis);
        trekking=view.findViewById(R.id.cv_fp_trekking);
        swimming=view.findViewById(R.id.cv_fp_swimming);
        running=view.findViewById(R.id.cv_fp_running);
        volleyball=view.findViewById(R.id.cv_fp_volleyball);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_fp_football:
                onClickFootball();
                break;
            case R.id.cv_fp_basketball:
                onClickBasketball();
                break;
            case R.id.cv_fp_volleyball:
                onClickVolleyball();
                break;
            case R.id.cv_fp_tennis:
                onClickTennis();
                break;
            case R.id.cv_fp_trekking:
                onClickTrekking();
                break;
            case R.id.cv_fp_table_tennis:
                onClickTableTennis();
                break;
            case R.id.cv_fp_running:
                onClickRunning();
                break;
            case R.id.cv_fp_swimming:
                onClickSwimming();
                break;
        }

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
        listener.inputStepOneSent(8);

    }

    private void onClickRunning() {
        listener.inputStepOneSent(6);

    }

    private void onClickTableTennis() {
        listener.inputStepOneSent(7);
    }

    private void onClickTrekking() {
        listener.inputStepOneSent(5);

    }

    private void onClickTennis() {
        listener.inputStepOneSent(4);

    }

    private void onClickVolleyball() {
        listener.inputStepOneSent(3);

    }

    private void onClickBasketball() {
        listener.inputStepOneSent(2);

    }

    private void onClickFootball() {
        listener.inputStepOneSent(1);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentStepOneListener){
            listener=(FragmentStepOneListener)context;
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
