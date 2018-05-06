package bro.id.siagaplus.Model;

/**
 * Created by Rafif on 03/05/2018.
 */

public class Checklist {
    private int id;
    private String title;
    private int cek;
    private int jenis;
    private boolean boll;

    public Checklist(){

    }

    public Checklist(String title, Boolean boll){
        this.title = title;
        this.boll = boll;
    }

    public Checklist(int id, int jenis, String title, int check) {
        this.id = id;
        this.jenis = jenis;
        this.title = title;
        this.cek = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCek() {
        return cek;
    }

    public void setCek(int cek) {
        this.cek = cek;
    }

    public int getJenis() {
        return jenis;
    }

    public void setJenis(int jenis) {
        this.jenis = jenis;
    }
}
