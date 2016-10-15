package pip.lesson7hw;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private final String baseURL = "http://finance.google.com/finance/info?client=iq&q=";
    private final String TAG = this.getClass().getName();

    private ArrayList<Ticker> tickers;
    private TickerAdapter tickersAdapter;
    private ListView listTickers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final Context c = this.getContext();

        listTickers = (ListView) view.findViewById(R.id.ticker_list);
        tickers = new ArrayList<>();
        tickersAdapter = new TickerAdapter(c, tickers);
        listTickers.setAdapter(tickersAdapter);

        return view;
    }

    /**
     * Displays alert dialog for user to insert a ticker name, called from activity fab button.
     */
    public void searchTicker() {
        final EditText input = new EditText(getContext());
        // set focus and popup keyboard
        input.setSelectAllOnFocus(true);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                .setTitle(R.string.enter_ticker_here)
                .setView(input)
                .setCancelable(true)
                .setPositiveButton(R.string.confirm_text_input, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // send a request for the stock
                        getStock(input.getText().toString());
                    }
                });

        AlertDialog visibleAlert = alert.create();
        // popup the soft keyboard when the alert dialog opens, close when alert dialog closes
        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        visibleAlert.show();
    }

    /**
     * Sends a request to find a stock value. If it fails to find a stock, it shows a not found snackbar.
     */
    private void getStock(String task) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseURL.concat(task),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // parse the response into proper values
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // snackbar to show the user that something went wrong
                Snackbar snackbar = Snackbar.make(getView(), "Not a valid stock", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                snackbar.show();
                Log.e(TAG, "Volley error");
            }
        });

        MySingleton.getInstance(this.getActivity()).addToRequestQueue(stringRequest);
    }

    /**
     * Parses the json response and adds the name and value of a stock to the list adapter view.
     */
    private void parseJSON(String response) {
        // Is the response guaranteed to have length > 7?
        String trimmed = response.substring(5, response.length() - 2);
        try {
            JSONObject j = new JSONObject(trimmed);
            // value at t is the name, value at l is the price
            String name = j.getString("t"); // Just being picky, but these strings could be private final
            double value = j.getDouble("l");
            // if the stock name is null, probably doesn't exist in the json and should error
            if (name != null) {
                tickersAdapter.add(new Ticker(name, value));
            } else {
                // snackbar to show the user that a stock could not be added
                Snackbar snackbar = Snackbar.make(getView(), "Unable to add stock", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                snackbar.show();
            }
        } catch (JSONException e) {
            // this should never be hit, but if it does things are bad
            e.printStackTrace();
        }
    }
}
