package com.example.assignment;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DigitalClock;
import android.widget.TextView;

import com.example.assignment.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    DigitalClock clock;
    TextView textviewDay, textviewWeekday;
    Typeface tf;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        clock = view.findViewById(R.id.digitalClock);
        textviewDay = view.findViewById(R.id.textView_date);
        textviewWeekday = view.findViewById(R.id.textView_weekday);

        tf = Typeface.createFromAsset(getActivity().getAssets(),"Caballar.ttf");
        clock.setTypeface(tf);

        String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        String weekday = new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date());

        textviewDay.setText(date);
        textviewDay.setTypeface(tf);

        textviewWeekday.setText(weekday);
        textviewWeekday.setTypeface(tf);
        return view;
    }

}
