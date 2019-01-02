package entities;

public class Grade {
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

    private int value;
    private int percentage;

    public Grade(int value, int percentage){
        this.value = value;
        this.percentage = percentage;
    }
}
