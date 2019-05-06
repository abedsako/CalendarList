package sako.abdelrahman.dateslist.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sako.abdelrahman.dateslist.R;

class DatesListViewHolder extends RecyclerView.ViewHolder {

    TextView day, month, year;
    ImageView circle;
    View sideLine,bottomLine;
    RelativeLayout mainLayout;

    DatesListViewHolder(@NonNull View itemView) {
        super(itemView);

        day = itemView.findViewById(R.id.DaysTextView);
        month = itemView.findViewById(R.id.MonthTextView);
        year = itemView.findViewById(R.id.YearTextView);

        mainLayout = itemView.findViewById(R.id.date_time_mainbackground);
        circle = itemView.findViewById(R.id.BackgroundCircle);
        sideLine = itemView.findViewById(R.id.SideLine);
        bottomLine = itemView.findViewById(R.id.BottomLine);
    }
}
