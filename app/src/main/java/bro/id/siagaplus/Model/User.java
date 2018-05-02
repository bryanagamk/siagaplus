package bro.id.siagaplus.Model;

public class User {
    public static final String TABLE_NAME = "Users";

    public static final String ID_USER = "id";
    public static final String NAME_USER = "name";

    private int id;
    private String name;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NAME_USER + " TEXT,"
                    + ")";

    public User(){

    }

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }



}
