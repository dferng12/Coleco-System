package Model;

public class SubjectHandler implements Handler{
    private String name;

    public SubjectHandler(String name){
        this.name = name;
    }

    @Override
    public boolean compareTo(Handler handler) {
        return handler.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        return "SUBJECT:" + this.name;
    }
}
