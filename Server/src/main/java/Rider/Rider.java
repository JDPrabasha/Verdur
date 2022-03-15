package Rider;

public class Rider {
    private String id;
    private String name;
    private int due;
    private String availible;

    public Rider(int due, String availible) {
        this.due = due;
        this.availible = availible;
    }
}
