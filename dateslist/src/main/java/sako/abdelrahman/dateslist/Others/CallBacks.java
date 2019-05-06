package sako.abdelrahman.dateslist.Others;

import android.view.View;

import java.util.ArrayList;

import sako.abdelrahman.dateslist.Model.DatesListModel;

public class CallBacks {

    public interface setResultsCallback{
        void results (ArrayList<DatesListModel> results);
    }

    public interface setOnItemClickListener {
        void onClick(View view, int position);
    }

}
