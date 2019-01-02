package entities;

import java.util.Date;

public class Absence{

    private Date date;
    private String reason;

    public Absence(Date date, String reason) {
        this.date = date;
        this.reason = reason;
    }
}
