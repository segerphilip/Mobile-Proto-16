package pip.lesson5HW;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment handling the separate to do items in a list
 */
public class NotesFragment extends Fragment {
    @BindView(R.id.noteList) ListView noteList;
    private ArrayList<Item> notes;
    private TodoAdapter notesAdapter;
    private NoteReaderDbHelper mHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        mHelper = new NoteReaderDbHelper(getContext());
        notes = mHelper.getAll();
        notesAdapter = new TodoAdapter(getActivity(), notes, mHelper);
        ButterKnife.bind(this, view);
        noteList.setAdapter(notesAdapter);

        // fab used to add completely new notes
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialog();
            }
        });

        return view;
    }

    /**
     * Removes checked to-do items and clears them from the adapter
     */
    public void clearDone() {
        for (int i = notes.size() - 1; i >= 0; i--) {
            Item toRemove = notes.get(i);
            if (toRemove.isDone()) {
                mHelper.removeItem(toRemove);
                notes.remove(i);
            }
        }
        // this is bad, but it makes the list work
        // TODO forced garbage collection is not great
        notesAdapter = new TodoAdapter(getActivity(), notes, mHelper);
        noteList.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();
    }

    /**
     * Creates an alert dialog with editable text box and saves the changes in the clicked note or new note
     */
    private void createAlertDialog() {
        final EditText input = new EditText(getContext());
        // set focus and popup keyboard
        input.setSelectAllOnFocus(true);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                .setMessage(R.string.newText)
                .setView(input)
                .setCancelable(true)
                .setPositiveButton(R.string.popupConfirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Item newItem = new Item(0, false, input.getText().toString());
                        String task = String.valueOf(input.getText().toString());
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(NoteReaderContract.NoteEntry.COLUMN_NAME_NOTE, task);
                        values.put(NoteReaderContract.NoteEntry.COLUMN_NAME_DONE, task);
                        db.insertWithOnConflict(NoteReaderContract.NoteEntry.TABLE_NAME,
                                null,
                                values,
                                SQLiteDatabase.CONFLICT_REPLACE);
                        db.close();
                        notesAdapter.add(newItem);
                    }
                });

        AlertDialog visibleAlert = alert.create();
        // popup the soft keyboard when the alert dialog opens, close when alert dialog closes
        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        notesAdapter.notifyDataSetChanged();
        visibleAlert.show();
    }
}
