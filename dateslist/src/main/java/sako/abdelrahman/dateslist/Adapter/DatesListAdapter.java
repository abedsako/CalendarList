package sako.abdelrahman.dateslist.Adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import sako.abdelrahman.dateslist.Model.DatesListModel;
import sako.abdelrahman.dateslist.Others.CallBacks;
import sako.abdelrahman.dateslist.Others.Dates;
import sako.abdelrahman.dateslist.Others.Options;
import sako.abdelrahman.dateslist.R;

public class DatesListAdapter extends RecyclerView.Adapter<DatesListViewHolder> {

    private RecyclerView recyclerView;
    private ArrayList<DatesListModel> list;
    private LayoutInflater inflater;
    private boolean monthAsText = false;
    private boolean dayAsText = false;
    private CallBacks.setOnItemClickListener onItemClickListener;
    private Options options;
    private float toAplpha = 0.5f;
    private int lastSelectedItemPos = 10000000;
    private List<Integer> selectedPositions;

    public DatesListAdapter(Activity activity) {
        init(activity);
    }

    public DatesListAdapter(Activity activity, CallBacks.setOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        init(activity);
    }

    private void init(Activity activity) {
        inflater = LayoutInflater.from(activity);
        list = new ArrayList<>();
        selectedPositions = new ArrayList<>();
        getOptions();
    }

    private void getOptions() {
        options = Options.getInstance();

        monthAsText = options.isMonthAsText();
        dayAsText = options.isDaysAsText();
    }


    @NonNull
    @Override
    public DatesListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.dateslist_row, viewGroup, false);
        return new DatesListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DatesListViewHolder holder, int position) {
        SetColors(holder);
        final DatesListModel data = list.get(position);

        if (data.isSelected()) {
            holder.itemView.animate().alpha(1).setDuration(0).start();
            holder.circle.animate().scaleX(2).scaleY(2).setDuration(0).start();
            holder.bottomLine.animate().scaleX(1).setDuration(0).start();
        } else {
            holder.itemView.animate().alpha(toAplpha).setDuration(0).start();
            holder.circle.animate().scaleX(0).scaleY(0).setDuration(0).start();
            holder.bottomLine.animate().scaleX(0).setDuration(0).start();
        }

        if (dayAsText) {
            holder.day.setText(data.getDayText());
        } else holder.day.setText(data.getDay());

        if (monthAsText) {
            holder.month.setText(data.getMonthText());
        } else holder.month.setText(data.getMonth());

        holder.year.setText(data.getYear());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(v, holder.getAdapterPosition());

                if (data.isSelected()) {
                    holder.itemView.animate().alpha(toAplpha).setDuration(200).start();
                    holder.circle.animate().scaleX(0).scaleY(0).setDuration(300).start();
                    holder.bottomLine.animate().scaleX(0).setDuration(300).start();

                    CheckIfMultiSelect(holder, true);

                    data.setSelected(false);
                } else {
                    holder.itemView.animate().alpha(1).setDuration(200).start();
                    holder.circle.animate().scaleX(2).scaleY(2).setDuration(300).start();
                    holder.bottomLine.animate().scaleX(1).setDuration(310).start();

                    CheckIfMultiSelect(holder, false);

                    data.setSelected(true);
                }
            }
        });

    }

    private void CheckIfMultiSelect(DatesListViewHolder holder, boolean isSelected) {
        int position = holder.getAdapterPosition();
        if (options != null) {
            if (options.isMultiSelect()) {
                multiSelection(position, holder, isSelected);
            } else {
                singleSelection(position, isSelected);
            }
        } else {
            singleSelection(position, isSelected);
        }
    }


    private void singleSelection(int position, boolean isSelected) {
        if (isSelected) {
            lastSelectedItemPos = 10000000;
        } else {
            if (lastSelectedItemPos != 10000000) {
                CheckIfOthersSelected(lastSelectedItemPos);
            }
            lastSelectedItemPos = position;
        }
    }

    private void multiSelection(int position, DatesListViewHolder holder, boolean isSelected) {

        if (isSelected) {
            if (selectedPositions.contains(position)) {
                switch (selectedPositions.size()) {
                    case 1:
                        list.get(position).setSelected(false);
                        unSelectOne(position);
                        holder.itemView.animate().alpha(toAplpha).setDuration(200).start();
                        holder.circle.animate().scaleX(0).scaleY(0).setDuration(300).start();
                        holder.bottomLine.animate().scaleX(0).setDuration(300).start();
                        break;
                    case 2:
                        list.get(position).setSelected(false);
                        unSelectOne(position);
                        holder.itemView.animate().alpha(toAplpha).setDuration(200).start();
                        holder.circle.animate().scaleX(0).scaleY(0).setDuration(300).start();
                        holder.bottomLine.animate().scaleX(0).setDuration(300).start();
                        break;
                }
            }
        } else {
            if (selectedPositions.size() == 2) {
                unSelectAll();
                selectedPositions.add(position);
            } else {
                selectedPositions.add(position);
            }
        }
    }

    private void unSelectAll() {
        for (int k = 0; k < selectedPositions.size(); k++) {
            int position = selectedPositions.get(k);
            CheckIfOthersSelected(position);
        }
        selectedPositions.clear();
    }

    private void unSelectOne(int position) {
        for (int k = 0; k < selectedPositions.size(); k++) {
            if (selectedPositions.get(k) == position) {
                selectedPositions.remove(k);
                k = selectedPositions.size() - 1;
            }
        }
    }

    private void CheckIfOthersSelected(int lastSelectedItemPos) {
        if (recyclerView != null && list.size() > 0) {
            LinearLayoutManager layoutManager = getLayoutManaget(recyclerView);

            if (isPositionViewVisible(lastSelectedItemPos, layoutManager)) {
                View view = layoutManager.findViewByPosition(lastSelectedItemPos);
                View line = layoutManager.findViewByPosition(lastSelectedItemPos).findViewById(R.id.BottomLine);
                ImageView circle = (ImageView) layoutManager.findViewByPosition(lastSelectedItemPos).findViewById(R.id.BackgroundCircle);

                if (view != null)
                    view.animate().alpha(toAplpha).setDuration(200).start();
                if (circle != null)
                    circle.animate().scaleX(0).scaleY(0).setDuration(300).start();
                if (line != null)
                    line.animate().scaleX(0).setDuration(300).start();

                list.get(lastSelectedItemPos).setSelected(false);
            } else {
                list.get(lastSelectedItemPos).setSelected(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                }, 300);
            }

        }
    }

    private static boolean isPositionViewVisible(int position, LinearLayoutManager layoutManager) {
        int first = layoutManager.findFirstVisibleItemPosition();
        int last = layoutManager.findLastVisibleItemPosition();

        if (position > last || position < first) {
            return false;
        } else {
            return true;
        }
    }

    private static LinearLayoutManager getLayoutManaget(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = null;
        if (recyclerView != null) {
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        }
        return layoutManager;
    }

    private void SetColors(DatesListViewHolder holder) {
        if (options != null) {
            holder.bottomLine.setBackgroundColor(options.getBottomLineColor());
            holder.day.setTextColor(options.getDayTextColor());
            holder.month.setTextColor(options.getMonthTextColor());
            holder.year.setTextColor(options.getYearTextColor());
            holder.sideLine.setBackgroundColor(options.getSideLineColor());
            toAplpha = options.getAnimateToAlpha();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getPossitionForDate(Dates dates) {
        int day = Integer.parseInt(dates.getCurrentDay());
        int month = Integer.parseInt(dates.getCurrentMonth());
        for (int k = 0; k < list.size(); k++) {
            int listDay = Integer.parseInt(list.get(k).getDay());
            int listMonth = Integer.parseInt(list.get(k).getMonth());
            if (listMonth == month && listDay == day) {
                return k;
            }
        }
        return 0;
    }

    public ArrayList<DatesListModel> getResults() {
        ArrayList<DatesListModel> results = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            boolean isItSelected = list.get(i).isSelected();
            if (isItSelected) {
                results.add(list.get(i));
            }
        }

        return results;
    }

    public void setList(ArrayList<DatesListModel> list) {
        this.list = list;
    }

    public ArrayList<DatesListModel> getList() {
        if (list != null)
            return list;
        return null;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


}
