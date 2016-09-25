package pip.lesson5HW;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class to perform db operations
 */
public class NoteReaderDbHelper extends SQLiteOpenHelper {
    // defs for standard db operations
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteReaderContract.NoteEntry.TABLE_NAME + " (" +
                    NoteReaderContract.NoteEntry._ID + " INTEGER PRIMARY KEY," +
                    NoteReaderContract.NoteEntry.COLUMN_NAME_NOTE + TEXT_TYPE + COMMA_SEP +
                    NoteReaderContract.NoteEntry.COLUMN_NAME_DONE + TEXT_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteReaderContract.NoteEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NoteReader.db";

    public NoteReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
