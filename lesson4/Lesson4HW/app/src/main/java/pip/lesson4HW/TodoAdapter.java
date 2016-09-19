package pip.lesson4HW;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Defines a custom adapter used to display a list of to-do items and a checkbox for complete/incomplete
 */
public class TodoAdapter extends ArrayAdapter<Item> {
    @BindView(R.id.todo_item_check) CheckBox itemCheck;
    @BindView(R.id.todo_item_text) TextView itemText;

    public TodoAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    /**
     * Creates custom list view for to-do items
     * Guide followed at: http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#using-a-custom-arrayadapter
     */
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        // get the current item
        final Item item = getItem(position);
        // check for reused view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }
        // create objects to assign data later
        ButterKnife.bind(this, convertView);
        // assign Item data
        itemCheck.setActivated(item.isDone());
        itemText.setText(item.getItem());

        itemCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if item is done, check its box and cross out its text
                if (!item.isDone()) {
                    item.setDone(true);
                    itemCheck.setActivated(true);
                    itemText.setPaintFlags(itemText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    item.setDone(false);
                    itemCheck.setActivated(false);
                    itemText.setPaintFlags(itemText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
                Log.d("TodoAdapter", "this " + itemCheck.isActivated());
            }
        });

        itemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialog(item);
                // set text to a new value
            }
        });

        return convertView;
    }

    /**
     * Creates an alert dialog to edit preexisting values in the listview
     */
    public void createAlertDialog(final Item item) {
        final String prevText = item.getItem();
        final EditText input = new EditText(getContext());
        // set the initial text when the alert dialog pops up if it is not new
        if (prevText != null) {
            input.setText(prevText);
            // select all the text and popup the keyboard to easily re-edit note
            input.setSelection(prevText.length());
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
                        item.setItem(input.getText().toString());
                    }
                });

        AlertDialog visibleAlert = alert.create();
        // popup the soft keyboard when the alert dialog opens, close when alert dialog closes
        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        visibleAlert.show();
    }
}
