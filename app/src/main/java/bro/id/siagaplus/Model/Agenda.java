package bro.id.siagaplus.Model;

/**
 * Created by Rafif on 03/05/2018.
 */

public class Agenda {
    private String tgl_agenda,waktu_agenda,title_agenda;
    private int id;

    public Agenda(String tgl_agenda, String waktu_agenda, String title_agenda) {
        this.tgl_agenda = tgl_agenda;
        this.waktu_agenda = waktu_agenda;
        this.title_agenda = title_agenda;
    }

    public String getTgl_agenda() {
        return tgl_agenda;
    }

    public void setTgl_agenda(String tgl_agenda) {
        this.tgl_agenda = tgl_agenda;
    }

    public String getWaktu_agenda() {
        return waktu_agenda;
    }

    public void setWaktu_agenda(String waktu_agenda) {
        this.waktu_agenda = waktu_agenda;
    }

    public String getTitle_agenda() {
        return title_agenda;
    }

    public void setTitle_agenda(String title_agenda) {
        this.title_agenda = title_agenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
