package pip.lesson8HW;

import android.provider.BaseColumns;

/**
 * Schema definition for note database
 */

public final class NoteReaderContract {

    // To prevent someone from accidentally instantiating the contract class, private
    private NoteReaderContract() {

    }

    /**
     * Table content defs.
     */
    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_NOTE = "content";
        public static final String COLUMN_NAME_DONE = "progress";
    }
}
