package bro.id.siagaplus.Model;

/**
 * Created by Rafif on 03/05/2018.
 */

public class Checklist {
    private String title;
    boolean check;

    public Checklist(String title, boolean check) {
        this.title = title;
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheck() {
        return check;
    }
}
