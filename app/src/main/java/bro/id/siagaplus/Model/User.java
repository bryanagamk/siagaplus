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
                    + NAME_USER + " TEXT"
                    + ")";

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }


}
