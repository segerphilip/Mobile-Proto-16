package pip.lesson5HW;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Helper class to perform db operations.
 */
public class NoteReaderDbHelper extends SQLiteOpenHelper {
    // defs for standard db operations
    public static final int DB_VERSION = 1;
    public static final String DATABASE_NAME = "NoteReader.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteReaderContract.NoteEntry.TABLE_NAME + " (" +
                    NoteReaderContract.NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NoteReaderContract.NoteEntry.COLUMN_NAME_NOTE + TEXT_TYPE + COMMA_SEP +
                    NoteReaderContract.NoteEntry.COLUMN_NAME_DONE + TEXT_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteReaderContract.NoteEntry.TABLE_NAME;

    public NoteReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
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

    /**
     * Returns an arraylist of all db entries, as Item.
     */
    public ArrayList<Item> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Item> allItems = new ArrayList<Item>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                NoteReaderContract.NoteEntry._ID,
                NoteReaderContract.NoteEntry.COLUMN_NAME_NOTE,
                NoteReaderContract.NoteEntry.COLUMN_NAME_DONE
        };

        String sortOrder = NoteReaderContract.NoteEntry._ID + " DESC";

        Cursor cursor = db.query(
                NoteReaderContract.NoteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        boolean notEmpty = cursor.moveToFirst();
        boolean isLast = cursor.isLast();

        while (!isLast && notEmpty) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(NoteReaderContract.NoteEntry._ID));
            String itemText = cursor.getString(
                    cursor.getColumnIndexOrThrow(NoteReaderContract.NoteEntry.COLUMN_NAME_NOTE));
            int itemDone = cursor.getInt(
                    cursor.getColumnIndexOrThrow(NoteReaderContract.NoteEntry.COLUMN_NAME_DONE));
            // need to convert int from db to boolean for Item
            /*
            Only complaining here because we know each other. But this could be a one-liner:
             */
            boolean isDone = (itemDone == 1);
//            boolean isDone = false;
//            if (itemDone == 1) {
//                isDone = true;
//            }
            allItems.add(new Item(itemId, isDone, itemText));
            isLast = cursor.isLast();
            cursor.moveToNext();
        }

        return allItems;
    }

    /**
     * Adds a new item to the db and returns it with an updated id.
     */
    public Item addItem(Item newItem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NoteReaderContract.NoteEntry.COLUMN_NAME_NOTE, newItem.getItem());
        values.put(NoteReaderContract.NoteEntry.COLUMN_NAME_DONE, newItem.isDone());
        long rowId = db.insert(NoteReaderContract.NoteEntry.TABLE_NAME, null, values);

        db.close();

        newItem.setId(rowId);

        return newItem;
    }

    /**
     * Removes a specific item from the db.
     */
    public void removeItem(Item toRemove) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = NoteReaderContract.NoteEntry._ID + " LIKE ?";
        String[] selectionArgs = {Long.toString(toRemove.getId())};

        db.delete(NoteReaderContract.NoteEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    /**
     * Updates a specific item in the db.
     */
    public void updateItem(Item toUpdate) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NoteReaderContract.NoteEntry.COLUMN_NAME_NOTE, toUpdate.getItem());
        values.put(NoteReaderContract.NoteEntry.COLUMN_NAME_DONE, toUpdate.isDone());

        String selection = NoteReaderContract.NoteEntry._ID + " LIKE ?";
        String[] selectionArgs = {Long.toString(toUpdate.getId())};

        db.update(NoteReaderContract.NoteEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }
}
