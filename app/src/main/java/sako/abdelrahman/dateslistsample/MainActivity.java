package sako.abdelrahman.dateslistsample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sako.abdelrahman.dateslist.DatesList;
import sako.abdelrahman.dateslist.Model.DatesListModel;
import sako.abdelrahman.dateslist.Others.CallBacks;
import sako.abdelrahman.dateslist.Others.Options;

public class MainActivity extends AppCompatActivity implements CallBacks.setOnItemClickListener {

    private Button restart, optionsBtn, scrollFirst, scrollLast,
            smoothScrollFirst, smoothScrollLast, scrollToCurrent;
    private TextView results;
    private SettingsFragment settingsFragment;
    private String fragmentTag = "settingsFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        restart = findViewById(R.id.restartActivityBTN);
        optionsBtn = findViewById(R.id.optionsBTN);
        smoothScrollFirst = findViewById(R.id.scrollToFirstOneBTN);
        scrollFirst = findViewById(R.id.scrollToFirstTwoBTN);
        scrollToCurrent = findViewById(R.id.scrollToCurrentBTN);
        scrollLast = findViewById(R.id.scrollToLastOneBTN);
        smoothScrollLast = findViewById(R.id.scrollToLastTwoBTN);

        results = findViewById(R.id.resultsBTN);

        //-------------

        Options options = Options.getInstance();
        options.setMultiSelect(true);

        settingsFragment = new SettingsFragment(); //-- Settings fragment

        // prepare the adapter and on click callback if you need it
        DatesList datesList = new DatesList(this, this);
        recyclerView.setAdapter(datesList.getDatesAdapter(recyclerView));

        buttonsClick(datesList);

    }

    /**
     * onClick : on item click callback
     */
    @Override
    public void onClick(View view, int position) {
    }

    private void buttonsClick(final DatesList datesList) {

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTheActivity();
            }
        });

        // open option fragment
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag(fragmentTag) == null) {
                    getSupportFragmentManager().beginTransaction().add(R.id.mainLayout, settingsFragment, fragmentTag).commitAllowingStateLoss();
                } else {
                    if (getSupportFragmentManager().findFragmentByTag(fragmentTag).isAdded()
                            && getSupportFragmentManager().findFragmentByTag(fragmentTag).isHidden()) {
                        getSupportFragmentManager().beginTransaction().show(settingsFragment).commitAllowingStateLoss();
                    } else {
                        getSupportFragmentManager().beginTransaction().add(R.id.mainLayout, settingsFragment).commitAllowingStateLoss();
                    }
                }
            }
        });

        // immediately go to the first date
        scrollFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datesList.scrollTo(DatesList.scrollTo.first, false);
            }
        });

        // immediately go to the last date
        scrollLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datesList.scrollTo(DatesList.scrollTo.last, false);
            }
        });

        // Smooth scroll to the current date
        scrollToCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datesList.scrollTo(DatesList.scrollTo.currentDate, false);
            }
        });

        // Smooth scroll to the first date
        smoothScrollFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datesList.scrollTo(DatesList.scrollTo.first, true);
            }
        });

        // Smooth scroll to the last date
        smoothScrollLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datesList.scrollTo(DatesList.scrollTo.last, true);
            }
        });


        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getResults(datesList);

            }
        });
    }

    /**
     * to get selected dates in list call getResults
     * the model contains day,month,year
     */

    private void getResults(final DatesList datesList) {
        datesList.getResults(new CallBacks.setResultsCallback() {
            @Override
            public void results(ArrayList<DatesListModel> results) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < results.size(); i++) {

                    String newline = "\n";
                    if (i == 0)
                        newline = "";

                    String day = results.get(i).getDay();
                    String month = results.get(i).getMonth();
                    String year = results.get(i).getYear();

                    builder.append(newline + day + "-" + month + "-" + year);
                }

                Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    // this method only restarts the activity
    // im using it to apply the changes
    private void ResetTheActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    // on back press im closing the fragment first if its open
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(fragmentTag) != null
                && getSupportFragmentManager().findFragmentByTag(fragmentTag).isResumed()) {
            getSupportFragmentManager().beginTransaction().remove(settingsFragment).commitAllowingStateLoss();
        } else {
            super.onBackPressed();
        }

    }
}
