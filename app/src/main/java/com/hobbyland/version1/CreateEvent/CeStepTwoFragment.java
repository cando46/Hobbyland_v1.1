package com.hobbyland.version1.CreateEvent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hobbyland.version1.FindPartner.FpStepTwoFragment;
import com.hobbyland.version1.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CeStepTwoFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private TextView dateDisplay;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private String date;
    private CeStepTwoListener listener;
    private TextView hobbyName;
    private ImageView hobby;
    private Spinner preferredSkillLevel;
    Button goNextStep;


    public CeStepTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public interface CeStepTwoListener {

        void inputCeStepTwoSent(int hobbyID);

    }

    public static CeStepTwoFragment newInstance(int hobbyId) {
        CeStepTwoFragment myFragment = new CeStepTwoFragment();

        Bundle args = new Bundle();
        args.putInt("HOBBY_ID", hobbyId);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ce_step_two, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        goNextStep=view.findViewById(R.id.btn_ce_set_location);
        preferredSkillLevel=view.findViewById(R.id.spinner_ce_skill_level);
        dateDisplay = view.findViewById(R.id.tv_ce_step2_select_date);
        dateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                date = dayOfMonth+ "/" +  month + "/" + year;
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(getContext(), timeSetListener, hour, minute, true);
                dialog.show();

            }
        };

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                date = date + "  " + hourOfDay + ":" + minute;
                dateDisplay.setText(date);
            }
        };

        hobby=view.findViewById(R.id.imv_ce_step2_hobby);
        hobbyName=view.findViewById(R.id.tv_ce_step2_hobbyName);
        //setting hobby name and hobby Image
        switch (getArguments().getInt("HOBBY_ID")) {
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

        //ADAPTER FOR PREFERRED SKILL LEVEL SPINNER
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.skill_level_preferred, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        preferredSkillLevel.setAdapter(adapter);
        preferredSkillLevel.setOnItemSelectedListener(this);

        //SET ON CLICK FOR NEXT BUTTON
        goNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edit later!!!!
                listener.inputCeStepTwoSent(0);
            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CeStepTwoListener) {
            listener = (CeStepTwoListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement CeStepTwoListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
