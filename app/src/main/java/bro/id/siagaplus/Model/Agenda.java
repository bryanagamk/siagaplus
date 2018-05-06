package bro.id.siagaplus.Model;

/**
 * Created by Rafif on 03/05/2018.
 */

public class Agenda {
    private String title;
    private String date;
    private int id;

    public Agenda() {
    }

    public Agenda(String title, int id, String date) {
        this.id = id;
        this.date = date;
        this.title = title;;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
