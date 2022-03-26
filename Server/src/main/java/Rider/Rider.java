package Rider;

public class Rider {
    private String id;
    private String name;
    private int due;
    private String contact;
    private String availible;

    public Rider(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public Rider(int due, String availible) {
        this.due = due;
        this.availible = availible;
    }
}
