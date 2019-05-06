package sako.abdelrahman.dateslist.Model;

public class DatesListModel {

    private String day, month, year, dayText , monthText;
    private boolean selected;

    public DatesListModel(String day, String month, String year, String dayText, String monthText,boolean selected) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayText = dayText;
        this.monthText = monthText;
        this.selected=selected;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDayText() {
        return dayText;
    }

    public void setDayText(String dayText) {
        this.dayText = dayText;
    }

    public String getMonthText() {
        return monthText;
    }

    public void setMonthText(String monthText) {
        this.monthText = monthText;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
