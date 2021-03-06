package bro.id.siagaplus.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.Model.Note;

public class DatabaseHelper extends SQLiteOpenHelper  {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "siagaplus_db";

    // Table Names
    private static final String TABLE_AGENDA = "agenda";
    private static final String TABLE_CHECKLIST = "checklist";

    // common column checklist names
    private static final String KEY_ID = "id";
    private static final String KEY_JENIS = "jenis";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CEK = "cek";

    // column agenda tables
    private static final String ID_AGENDA = "id";
    private static final String TITLE_AGENDA = "title";
    private static final String DATE_AGENDA = "date";

    // todo_tag table create statement
    private static final String CREATE_TABLE_CHECKLIST = "CREATE TABLE "
            + TABLE_CHECKLIST + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_JENIS + " INTEGER,"
            + KEY_TITLE + " STRING," + KEY_CEK + " INTEGER)";

    private static final String CREATE_TABLE_AGENDA = "CREATE TABLE "
            + TABLE_AGENDA + "(" + ID_AGENDA + " INTEGER PRIMARY KEY AUTOINCREMENT," + TITLE_AGENDA + " STRING,"
            + DATE_AGENDA + " DATETIME)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Note.CREATE_TABLE);
        db.execSQL(CREATE_TABLE_CHECKLIST);
        db.execSQL(CREATE_TABLE_AGENDA);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);

        // Create tables again
        onCreate(db);
    }

    // Delete all table
    public void deleteAll(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + Note.TABLE_NAME);
        db.execSQL("delete from " + TABLE_AGENDA);
        db.execSQL("delete from " + TABLE_CHECKLIST);

        db.close();
    }

    public long insertNote(String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Note.COLUMN_NOTE, note);

        // insert row
        long id = db.insert(Note.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Note getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
                Note.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }


    /**
     * Creating Checklist
     */
    public long createChecklist(long id, long jenis, String title, int cek) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_JENIS, jenis);
        values.put(KEY_TITLE, title);
        values.put(KEY_CEK, cek);

        return db.insert(TABLE_CHECKLIST, null, values);
    }

    /**
     * Updating a todo tag
     */

    public void checkedChecklist(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CEK, 1);

        // updating row
        db.update(TABLE_CHECKLIST, values, KEY_ID + " = "+ id, null);
    }

    public void uncheckedChecklist(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CEK, 0);

        // updating row
        db.update(TABLE_CHECKLIST, values, KEY_ID + " = "+ id, null);
    }

    public int updateChecklist(long id, String title) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);

        // updating row
        return db.update(TABLE_CHECKLIST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /**
     * Deleting a todo tag
     */
    public void deleteChecklist(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHECKLIST, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public long insertAgenda(String title, String date) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` will be inserted automatically.
        // no need to add them
        values.put(TITLE_AGENDA, title);
        values.put(DATE_AGENDA, date);

        // insert row
        long id = db.insert(TABLE_AGENDA, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Agenda getAgenda(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AGENDA,
                new String[]{ID_AGENDA, TITLE_AGENDA, DATE_AGENDA},
                ID_AGENDA + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Agenda agenda = new Agenda(
                cursor.getString(cursor.getColumnIndex(TITLE_AGENDA)),
                cursor.getInt(cursor.getColumnIndex(ID_AGENDA)),
                cursor.getString(cursor.getColumnIndex(DATE_AGENDA)));

        // close the db connection
        cursor.close();

        return agenda;
    }

    public ArrayList<Agenda> getAllAgenda() {
        ArrayList<Agenda> agendas = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_AGENDA + "";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Agenda agenda = new Agenda();
                agenda.setId(cursor.getInt(cursor.getColumnIndex(ID_AGENDA)));
                agenda.setDate(cursor.getString(cursor.getColumnIndex(DATE_AGENDA)));
                agenda.setTitle(cursor.getString(cursor.getColumnIndex(TITLE_AGENDA)));

                agendas.add(agenda);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return agendas;
    }

    public int getAgendasCount() {
        String countQuery = "SELECT  * FROM " + TABLE_AGENDA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateAgenda(Agenda agenda) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE_AGENDA, agenda.getTitle());
        values.put(DATE_AGENDA, agenda.getDate());

        // updating row
        return db.update(TABLE_AGENDA, values, ID_AGENDA + " = ?",
                new String[]{String.valueOf(agenda.getId())});
    }

    public void deleteAgenda(Agenda agenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AGENDA, ID_AGENDA + " = ?",
                new String[]{String.valueOf(agenda.getId())});
        db.close();
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public ArrayList<Checklist> getAllChecklist(int id) {
        ArrayList<Checklist> notes = new ArrayList<>();
        int queryid = id;
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHECKLIST + " WHERE jenis = " + queryid;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Checklist checklist = new Checklist(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getInt(cursor.getColumnIndex(KEY_JENIS)),
                        cursor.getString(cursor.getColumnIndex(KEY_TITLE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_CEK))
                );
                notes.add(checklist);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
