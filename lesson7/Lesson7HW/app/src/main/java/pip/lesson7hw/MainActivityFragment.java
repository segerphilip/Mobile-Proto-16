package pip.lesson7hw;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;

import org.json.JSONException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private final String TAG = this.getClass().getName();

    private Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            // YOUR CODE HERE. DO SOMETHING WHEN A RESPONSE COMES IN.
            // Hint: remove the first three characters, parse the response into a JSONArray,
            // and pass it into your extractPriceFromJSON() function.
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "A VolleyError occurred.");
            error.printStackTrace();
        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        final Context c = this.getContext();

                // YOUR CODE HERE.
                //
                // Create a StringRequest using the URL and the listeners declared above.
                // Add the request to your RequestQueue from your MySingleton class

        return view;
    }

    private String buildSearchURL(String companyTicker) {
        // YOUR CODE HERE
        // USE URIBuilder
        return "";
    }

    private String extractPriceFromJSON(JSONArray array) throws JSONException {
        // Your code here. Extract the price value from the JSON array
        return "";
    }

    /**
     * Displays alert dialog for user to insert a ticker name, called from activity fab button.
     */
    public void searchTicker() {
        final EditText input = new EditText(getContext());
        // set focus and popup keyboard
        input.setSelectAllOnFocus(true);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                .setMessage(R.string.enter_ticker_here)
                .setView(input)
                .setCancelable(true)
                .setPositiveButton(R.string.confirm_text_input, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String task = String.valueOf(input.getText().toString());
                    }
                });

        AlertDialog visibleAlert = alert.create();
        // popup the soft keyboard when the alert dialog opens, close when alert dialog closes
        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        visibleAlert.show();
    }
}
