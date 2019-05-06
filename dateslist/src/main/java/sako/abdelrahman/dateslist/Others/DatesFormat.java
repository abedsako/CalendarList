package sako.abdelrahman.dateslist.Others;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DatesFormat {

    private Locale locale;
    private SimpleDateFormat dateFormat;

    DatesFormat(Locale locale) {
        this.locale = locale;
    }

    SimpleDateFormat dayFormatter(boolean asText) {
        dateFormat = null;
        if (asText) {
            dateFormat = new SimpleDateFormat("EEE", locale);
        } else {
            dateFormat = new SimpleDateFormat("dd", locale);
        }
        return dateFormat;
    }

    SimpleDateFormat monthFormatter(boolean asText) {
        dateFormat = null;
        if (asText) {
            dateFormat = new SimpleDateFormat("MMM", locale);
        } else {
            dateFormat = new SimpleDateFormat("MM", locale);
        }
        return dateFormat;
    }

    public SimpleDateFormat yearFormatter() {
        dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyy", locale);
        return dateFormat;
    }

    SimpleDateFormat fullDateFormat() {
        dateFormat = null;
        dateFormat = new SimpleDateFormat("dd,MM", locale);
        return dateFormat;
    }

}
