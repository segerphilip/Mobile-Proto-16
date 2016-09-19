package pip.lesson4HW;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Fragment handling the separate to do items in a list
 */
public class NotesFragment extends Fragment {
    private ArrayList<Item> notes;
    private TodoAdapter notesAdapter;
    private ListView noteList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        // create all the necessary text boxes for the "to-do" list

        notes = new ArrayList<Item>();
        notesAdapter = new TodoAdapter(getActivity(), notes);
        noteList = (ListView) view.findViewById(R.id.noteList);
        noteList.setAdapter(notesAdapter);

        Item it1 = new Item(false, "test");
        Item it2 = new Item(false, "tlksjdsf");
        notes.add(it1);
        notes.add(it2);

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

    public void clearDone() {
        // using a while loop because removing items from arraylist changes index of contents
        int i = 0;
        while (i < notes.size()) {
            Item toRemove = notes.get(i);
            if (toRemove.isDone()) {
                notes.remove(i);
            } else {
                i++;
            }
        }

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
                        Item newItem = new Item(false, input.getText().toString());
                        notesAdapter.add(newItem);
                    }
                });

        AlertDialog visibleAlert = alert.create();
        // popup the soft keyboard when the alert dialog opens, close when alert dialog closes
        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        visibleAlert.show();
    }
}
