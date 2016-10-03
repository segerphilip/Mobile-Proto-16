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

import org.json.JSONArray;
import org.json.JSONException;

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
        tickers = new ArrayList<Ticker>();
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
        final String jsonResponse = "";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseURL.concat(task),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar snackbar = Snackbar.make(getView(), "Unable to add stock", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                snackbar.show();
                Log.e(TAG, "Volley error");
            }
        });

        MySingleton.getInstance(this.getActivity()).addToRequestQueue(stringRequest);
    }

    private void parseJSON(String response) {
        Log.d(TAG, response);
    }
}
