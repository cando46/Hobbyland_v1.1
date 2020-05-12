package com.hobbyland.version1.FindPartner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hobbyland.version1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FpStepTwoFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner yearOfExperience;
    ImageView hobby;
    TextView hobbyName;

    public FpStepTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fp_step_two, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        hobby=view.findViewById(R.id.imv_step2_hobby);
        hobbyName=view.findViewById(R.id.tv_step2_hobbyName);

        yearOfExperience=view.findViewById(R.id.spinner_step2_year_of_exp);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this.getContext(),
                R.array.year_of_exp,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearOfExperience.setAdapter(adapter);
        yearOfExperience.setOnItemSelectedListener(this);

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
        switch (getArguments().getInt("HOBBY_ID",0)){
            case 1:
                hobbyName.setText("Football");
                hobby.setImageResource(R.drawable.football);
                break;
            case 2:
                hobbyName.setText("Basketball");
                hobby.setImageResource(R.drawable.basketball);
                break;
            case 3:
                hobbyName.setText("Voleyball");
                hobby.setImageResource(R.drawable.volleyball);
                break;
            case 4:
                hobbyName.setText("Tennis");
                hobby.setImageResource(R.drawable.tennis);
                break;
            case 5:
                hobbyName.setText("Trekking");
                hobby.setImageResource(R.drawable.trekking);
                break;
            case 6:
                hobbyName.setText("Running");
                hobby.setImageResource(R.drawable.runing);
                break;
            case 7:
                hobbyName.setText("Table Tennis");
                hobby.setImageResource(R.drawable.table_tennis);
                break;
            case 8:
                hobbyName.setText("Swimming");
                hobby.setImageResource(R.drawable.swimming);
                break;
            default:
                hobbyName.setText("No Hobby Selected");
                break;
        }

    }

    public static FpStepTwoFragment newInstance(int hobbyId) {
        FpStepTwoFragment myFragment = new FpStepTwoFragment();

        Bundle args = new Bundle();
        args.putInt("HOBBY_ID", hobbyId);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
