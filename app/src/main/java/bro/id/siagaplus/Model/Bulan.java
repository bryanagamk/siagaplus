package bro.id.siagaplus.Model;

/**
 * Created by Rafif on 03/05/2018.
 */

public class Bulan {
    private String bulan;
    private int id;

    public Bulan(String bulan, int id) {
        this.bulan = bulan;
        this.id = id;
    }

    public String getBulan() {
        return bulan;
    }

    public int getId() {
        return id;
    }
}
