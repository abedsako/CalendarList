package sako.abdelrahman.dateslist.Others;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import sako.abdelrahman.dateslist.Model.DatesListModel;

public class Dates {

    private String language = "en";
    private String timeZone = "";
    private boolean enableShowFullYear = true;
    private boolean enableDateAsText = false;

    private DatesFormat datesFormat;

    public Dates() {
        Locale locale = new Locale(language);
        datesFormat = new DatesFormat(locale);
        SetOptions();
    }

    private void SetOptions() {
        Options options = Options.getInstance();

        enableShowFullYear = options.isShowFullYearDates();

        if (options.isMonthAsText() || options.isDaysAsText())
            enableDateAsText = true;


    }

    public ArrayList<DatesListModel> getDateList() {

        ArrayList<DatesListModel> list = new ArrayList<>();

        Calendar currentCalendar = getCalender();
        Calendar fullCalender = getFullCalender(currentCalendar.get(Calendar.YEAR));

        int startMonth = 0;

        for (int months = startMonth; months <= getStopMonth(currentCalendar); months++) {
            DaysLoop(fullCalender, currentCalendar, list);
        }

        return list;
    }

    private void DaysLoop(Calendar fullCalender, Calendar currentCalender, ArrayList<DatesListModel> list) {
        int daysInMonth = fullCalender.getActualMaximum(Calendar.DATE);

        for (int startDay = 1; startDay <= daysInMonth; startDay++) {

            String fDay = datesFormat.dayFormatter(false).format(fullCalender.getTime());
            String fMonth = datesFormat.monthFormatter(false).format(fullCalender.getTime());
            String dayText = "";
            String monthText = "";

            if (enableDateAsText) {
                dayText = datesFormat.dayFormatter(true).format(fullCalender.getTime());
                monthText = datesFormat.monthFormatter(true).format(fullCalender.getTime());
            }

            String year = String.valueOf(fullCalender.get(Calendar.YEAR));

            int fNumDay = Integer.parseInt(fDay);
            int fNumMonth = Integer.parseInt(fMonth);

            if (!enableShowFullYear) {

                String cDay = datesFormat.dayFormatter(false).format(currentCalender.getTime());
                String cMonth = datesFormat.monthFormatter(false).format(currentCalender.getTime());

                int cNumDay = Integer.parseInt(cDay);
                int cNumMonth = Integer.parseInt(cMonth);

                if (fNumMonth < cNumMonth) {
                    list.add(new DatesListModel(fDay, fMonth, year, dayText, monthText, false));
                } else if (fNumMonth == cNumMonth) {
                    if (fNumDay < cNumDay) {
                        list.add(new DatesListModel(fDay, fMonth, year, dayText, monthText, false));
                    } else if (fNumDay == cNumDay) {
                        list.add(new DatesListModel(fDay, fMonth, year, dayText, monthText, false));
                    } else {
                        startDay = daysInMonth;
                    }
                } else {
                    startDay = daysInMonth;
                }

            } else {
                list.add(new DatesListModel(fDay, fMonth, year, dayText, monthText, false));
            }

            fullCalender.add(Calendar.DAY_OF_MONTH, 1);
        }

    }

    private Calendar getCalender() {
        Calendar calendar = Calendar.getInstance();
        if (!timeZone.isEmpty())
            calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        return calendar;
    }

    private Calendar getFullCalender(int year) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, year);
        return calendar;
    }

    private int getStopMonth(Calendar calendar) {
        if (!enableShowFullYear) {
            return calendar.get(Calendar.MONTH);
        } else {
            return 11;
        }
    }

    //-----------------------------------------

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void stopOnCurrentDate(boolean enable) {
        this.enableShowFullYear = enable;
    }

    public void dateAsText(boolean enable) {
        this.enableDateAsText = enable;
    }

    public String getCurrentDay() {
        return datesFormat.dayFormatter(false).format(getCalender().getTime());
    }

    public String getCurrentMonth() {
        return datesFormat.monthFormatter(false).format(getCalender().getTime());
    }

}
