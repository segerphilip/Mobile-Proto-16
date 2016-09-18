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
import android.widget.EditText;
import android.widget.TextView;

/**
 * Fragment handling the separate to do items in a list
 */
public class NotesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        // Create all the necessary text boxes for the "to-do" list
        final TextView text1 = (TextView) view.findViewById(R.id.fragmentNote1);
        final TextView text2 = (TextView) view.findViewById(R.id.fragmentNote2);
        final TextView text3 = (TextView) view.findViewById(R.id.fragmentNote3);
        final TextView text4 = (TextView) view.findViewById(R.id.fragmentNote4);
        final TextView text5 = (TextView) view.findViewById(R.id.fragmentNote5);

        text1.setOnClickListener(textNote(text1));
        text2.setOnClickListener(textNote(text2));
        text3.setOnClickListener(textNote(text3));
        text4.setOnClickListener(textNote(text4));
        text5.setOnClickListener(textNote(text5));

        return view;
    }

    public void createNewNote(String content) {
        final TextView text = new TextView(getContext());
        text.setText(content);
        text.setOnClickListener(textNote(text));
    }

    /**
     * OnClick for text views, used to remove repetition from onCreateView
     */
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
    private void createAlertDialog(final TextView text) {
        final EditText input = new EditText(getContext());
        // set the initial text when the alert dialog pops up
        input.setText(text.getText().toString());
        // select all the text and popup the keyboard to easily re-edit note
        input.setSelection(text.getText().length());
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
                        text.setText(input.getText().toString());
                    }
                });

        AlertDialog visibleAlert = alert.create();
        // popup the soft keyboard when the alert dialog opens, close when alert dialog closes
        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        visibleAlert.show();
    }
}
