package bro.id.siagaplus.Model;

/**
 * Created by Rafif on 03/05/2018.
 */

public class Artikel {
    private String title_artikel, menu_artikel, gambar_artikel, file_artikel;

    public Artikel(String title_artikel, String menu_artikel, String gambar_artikel, String file_artikel) {
        this.title_artikel = title_artikel;
        this.menu_artikel = menu_artikel;
        this.gambar_artikel = gambar_artikel;
        this.file_artikel = file_artikel;
    }

    public String getMenu_artikel() {
        return menu_artikel;
    }

    public String getGambar_artikel() {
        return gambar_artikel;
    }

    public String getFile_artikel() {
        return file_artikel;
    }

    public String getTitle_artikel() {
        return title_artikel;
    }
}
