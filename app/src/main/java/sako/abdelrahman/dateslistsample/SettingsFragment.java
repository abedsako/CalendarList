package sako.abdelrahman.dateslistsample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import sako.abdelrahman.dateslist.Others.Options;


public class SettingsFragment extends Fragment {


    public SettingsFragment() {
    }

    private Options options;
    private AppCompatCheckBox orientation, multi, day, month, fullyear;
    private LinearLayout Lorientation, Lmulti, Lday, Lmonth, Lfullyear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        orientation = view.findViewById(R.id.OrientationCheckbox);
        multi = view.findViewById(R.id.MultiCheckbox);
        day = view.findViewById(R.id.dayAsTextCheckbox);
        month = view.findViewById(R.id.monthAsTextCheckbox);
        fullyear = view.findViewById(R.id.ShowFullYearCheckbox);

        Lorientation = view.findViewById(R.id.OrientationLayout);
        Lmulti = view.findViewById(R.id.MultiLayout);
        Lday = view.findViewById(R.id.dayAsTextLayout);
        Lmonth = view.findViewById(R.id.monthAsTextLayout);
        Lfullyear = view.findViewById(R.id.ShowFullYearLayout);

        options = Options.getInstance();

        if (options.isListVerticalOrientation()) {
            orientation.setChecked(true);
        }
        if (options.isMultiSelect()) {
            multi.setChecked(true);
        }
        if (options.isDaysAsText()) {
            day.setChecked(true);
        }
        if (options.isMonthAsText()) {
            month.setChecked(true);
        }
        if (options.isShowFullYearDates()) {
            fullyear.setChecked(true);
        }

        Clickes();

        return view;
    }

    private void Clickes() {

        Lorientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.isListVerticalOrientation()) {
                    orientation.setChecked(false);
                    options.setListVerticalOrientation(false);
                } else {
                    orientation.setChecked(true);
                    options.setListVerticalOrientation(true);
                }
            }
        });

        Lmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.isMultiSelect()) {
                    multi.setChecked(false);
                    options.setMultiSelect(false);
                } else {
                    multi.setChecked(true);
                    options.setMultiSelect(true);
                }
            }
        });

        Lday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.isDaysAsText()) {
                    day.setChecked(false);
                    options.setDaysAsText(false);
                } else {
                    day.setChecked(true);
                    options.setDaysAsText(true);
                }

            }
        });

        Lmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.isMonthAsText()) {
                    month.setChecked(false);
                    options.setMonthAsText(false);
                } else {
                    month.setChecked(true);
                    options.setMonthAsText(true);
                }

            }
        });


        Lfullyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.isShowFullYearDates()) {
                    fullyear.setChecked(false);
                    options.setShowFullYearDates(false);
                } else {
                    fullyear.setChecked(true);
                    options.setShowFullYearDates(true);
                }
            }
        });


    }

}
