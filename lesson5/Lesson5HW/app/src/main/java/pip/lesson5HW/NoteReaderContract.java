package pip.lesson5HW;

import android.provider.BaseColumns;

/**
 * Schema definition for note database
 */

public final class NoteReaderContract {
    // defs for standard db operations
    public static final String DATABASE_NAME = "NoteReader.db";
    public static final int DB_VERSION = 1;

    // To prevent someone from accidentally instantiating the contract class, private
    private NoteReaderContract() {

    }

    /**
     * Table content defs
     */
    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_NOTE = "content";
        public static final String COLUMN_NAME_DONE = "progress";
    }
}
