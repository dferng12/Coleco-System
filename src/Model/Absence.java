package Model;

import java.util.Date;

public class Absence{

    private Date date;
    private String reason;
    private int penalty;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }


    public Absence(Date date, String reason, int penalty) {
        this.date = date;
        this.reason = reason;
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return date.toString() + "::" + reason + ":: -" + String.valueOf(penalty);
    }
}
