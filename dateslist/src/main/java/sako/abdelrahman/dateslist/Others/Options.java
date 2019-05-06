package sako.abdelrahman.dateslist.Others;


public class Options {

    private int bottomLineColor = android.graphics.Color.parseColor("#00AC12");
    private int dayTextColor = android.graphics.Color.parseColor("#242424");
    private int monthTextColor = android.graphics.Color.parseColor("#5E5E5E");
    private int yearTextColor = android.graphics.Color.parseColor("#5E5E5E");
    private int sideLineColor = android.graphics.Color.parseColor("#444444");
    private int backgroundColor = android.graphics.Color.parseColor("#ffffff");
    private float animateToAlpha = 0.5f;
    private boolean multiSelect = false;
    private boolean ShowFullYearDates = true;
    private boolean monthAsText = false;
    private boolean daysAsText = false;
    private boolean ListVerticalOrientation = false;

    private static Options instance;

    private Options() {
    }

    public static Options getInstance() {
        if (instance == null) return instance = new Options();
        return instance;
    }

    public int getBottomLineColor() {
        return bottomLineColor;
    }

    public void setBottomLineColor(int bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
    }

    public int getDayTextColor() {
        return dayTextColor;
    }

    public void setDayTextColor(int dayTextColor) {
        this.dayTextColor = dayTextColor;
    }

    public int getMonthTextColor() {
        return monthTextColor;
    }

    public void setMonthTextColor(int monthTextColor) {
        this.monthTextColor = monthTextColor;
    }

    public int getYearTextColor() {
        return yearTextColor;
    }

    public void setYearTextColor(int yearTextColor) {
        this.yearTextColor = yearTextColor;
    }

    public int getSideLineColor() {
        return sideLineColor;
    }

    public void setSideLineColor(int sideLineColor) {
        this.sideLineColor = sideLineColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float getAnimateToAlpha() {
        return animateToAlpha;
    }

    public void setAnimateToAlpha(float animateToAlpha) {
        this.animateToAlpha = animateToAlpha;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public boolean isShowFullYearDates() {
        return ShowFullYearDates;
    }

    public void setShowFullYearDates(boolean showFullYearDates) {
        ShowFullYearDates = showFullYearDates;
    }

    public boolean isMonthAsText() {
        return monthAsText;
    }

    public void setMonthAsText(boolean monthAsText) {
        this.monthAsText = monthAsText;
    }

    public boolean isDaysAsText() {
        return daysAsText;
    }

    public void setDaysAsText(boolean daysAsText) {
        this.daysAsText = daysAsText;
    }

    public boolean isListVerticalOrientation() {
        return ListVerticalOrientation;
    }

    public void setListVerticalOrientation(boolean listVerticalOrientation) {
        ListVerticalOrientation = listVerticalOrientation;
    }
}
