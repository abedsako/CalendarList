package sako.abdelrahman.dateslist;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import sako.abdelrahman.dateslist.Adapter.DatesListAdapter;
import sako.abdelrahman.dateslist.Model.DatesListModel;
import sako.abdelrahman.dateslist.Others.CallBacks;
import sako.abdelrahman.dateslist.Others.Dates;
import sako.abdelrahman.dateslist.Others.Options;

public class DatesList {

    private Activity activity;
    private DatesListAdapter adapter;
    private RecyclerView recyclerView;
    private Dates dates;


    public DatesList(Activity activity, CallBacks.setOnItemClickListener onItemClickListener) {
        this.activity = activity;
        dates = new Dates();
        adapter = new DatesListAdapter(activity, onItemClickListener);
    }

    public DatesList(Activity activity) {
        this.activity = activity;
        dates = new Dates();
        adapter = new DatesListAdapter(activity);
    }

    private void SetAdapterList() {
        adapter.setList(dates.getDateList());
    }

    public DatesListAdapter getDatesAdapter(@Nullable RecyclerView recyclerView) {
        if (recyclerView != null) {
            this.recyclerView = recyclerView;
            adapter.setRecyclerView(recyclerView);

            Options options = Options.getInstance();
            if (options.isListVerticalOrientation()) {
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
            }

        }
        SetAdapterList();
        return adapter;
    }

    public DatesListAdapter getDatesAdapter(@Nullable RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        if (recyclerView != null && linearLayoutManager != null) {
            this.recyclerView = recyclerView;
            adapter.setRecyclerView(recyclerView);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        SetAdapterList();
        return adapter;
    }

    //-----------
    private void scrollToFirstDate(boolean smoothScroll) {
        if (recyclerView != null) {
            if (adapter.getItemCount() > 0) {
                if (smoothScroll) {
                    recyclerView.smoothScrollToPosition(0);
                } else recyclerView.scrollToPosition(0);
            }
        }
    }

    private void scrollToCurrentDate(boolean smoothScroll) {
        if (recyclerView != null) {
            if (dates != null) {
                int position = adapter.getPossitionForDate(dates);
                if (smoothScroll) {
                    recyclerView.smoothScrollToPosition(position);
                } else recyclerView.scrollToPosition(position);
            }
        }
    }

    private void scrollToLastDate(boolean smoothScroll) {
        if (recyclerView != null) {
            if (adapter.getItemCount() > 0) {
                if (smoothScroll) {
                    recyclerView.smoothScrollToPosition(adapter.getList().size() - 1);
                } else recyclerView.scrollToPosition(adapter.getList().size() - 1);
            }
        }
    }
    //-----------

    public int getItemsSize() {
        return adapter.getItemCount();
    }

//    public ArrayList<DatesListModel> getResults() {
//        if (adapter != null)
//            return adapter.getResults();
//        return null;
//    }

    public void getResults(CallBacks.setResultsCallback resultsCallback) {
        if (adapter != null)
            resultsCallback.results(adapter.getResults());
    }

    public void setTextLocalLanguage(String language) {
        dates.setLanguage(language);
    }

    public void setTimeZone(String timeZone) {
        dates.setTimeZone(timeZone);
    }

    public void scrollTo(scrollTo scrollTo, boolean smoothScroll) {
        switch (scrollTo) {
            case first:
                scrollToFirstDate(smoothScroll);
                break;
            case currentDate:
                scrollToCurrentDate(smoothScroll);
                break;
            case last:
                scrollToLastDate(smoothScroll);
                break;
        }
    }

    public enum scrollTo {
        first, currentDate, last
    }


}
