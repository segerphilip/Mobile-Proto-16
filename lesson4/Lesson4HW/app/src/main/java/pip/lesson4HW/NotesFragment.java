package pip.lesson4HW;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Fragment handling the separate to do items in a list
 */
public class NotesFragment extends Fragment {
    private ArrayList<String> notes;
    private ArrayAdapter<String> notesAdapter;
    private ListView noteList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        // Create all the necessary text boxes for the "to-do" list

        noteList = (ListView) view.findViewById(R.id.noteList);
        notes = new ArrayList<String>();
        notesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, notes);
        noteList.setAdapter(notesAdapter);
        notes.add("First Item");
        notes.add("Second Item");

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
                notes.get(position);
                notes.set(position, "new value");
                notesAdapter.notifyDataSetChanged();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialog(null);
            }
        });

        return view;
    }

    private View.OnClickListener textNote(final TextView text) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NotesFragment", "TextView clicked" + text);
                createAlertDialog(text);
            }
        };
    }

    /**
     * Creates an alert dialog with editable text box and saves the changes in the clicked note
     */
    private void createAlertDialog(String text) {
        final EditText input = new EditText(getContext());
        // set the initial text when the alert dialog pops up if it is not new
        if (text != null) {
            input.setText(text);
            // select all the text and popup the keyboard to easily re-edit note
            input.setSelection(text.length());
        }
        // set focus and popup keyboard
        input.setSelectAllOnFocus(true);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                .setMessage(R.string.editableText)
                .setView(input)
                .setCancelable(true)
                .setPositiveButton(R.string.popupConfirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // set the note text when click Done
                        notes.add(input.getText().toString());
                    }
                });

        AlertDialog visibleAlert = alert.create();
        // popup the soft keyboard when the alert dialog opens, close when alert dialog closes
        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        visibleAlert.show();
    }
}
