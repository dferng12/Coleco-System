package Model;

public class Grade {
    private int value;
    private int percentage;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Grade(int value, int percentage){
        this.value = value;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return String.valueOf(value) + " :: " + String.valueOf(percentage) + "%";
    }
}
