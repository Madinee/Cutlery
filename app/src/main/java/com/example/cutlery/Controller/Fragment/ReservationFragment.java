package com.example.cutlery.Controller.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cutlery.R;

import java.util.Calendar;


public class ReservationFragment extends Fragment implements
        View.OnClickListener {
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private EditText name, phone_number, address, number_of_poeple;
    private Button confirmBtn;

    public ReservationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reservation, container, false);

        //find view
        btnDatePicker=view.findViewById(R.id.btn_date);
        btnTimePicker=view.findViewById(R.id.btn_time);
        txtDate=view.findViewById(R.id.in_date);
        txtTime=view.findViewById(R.id.in_time);


        number_of_poeple = view.findViewById(R.id.number_of_poeple);
        name=view.findViewById(R.id.name);
        phone_number=view.findViewById(R.id.phone_number);
        address=view.findViewById(R.id.address);
        confirmBtn=view.findViewById(R.id.confirmBtn);



        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == confirmBtn) {
            Check();
        }

    }


    private void Check()
    {
        if (TextUtils.isEmpty(name.getText().toString()))
        {
            Toast.makeText(getActivity(), "Please provide your full name.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone_number.getText().toString()))
        {
            Toast.makeText(getActivity(), "Please provide your phone number.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(address.getText().toString()))
        {
            Toast.makeText(getActivity(), "Please provide your email address.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(number_of_poeple.getText().toString()))
        {
            Toast.makeText(getActivity(), "Please provide number of people.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //ConfirmOrder();
        }
    }
}
