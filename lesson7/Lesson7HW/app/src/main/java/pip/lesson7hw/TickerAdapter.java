package pip.lesson7hw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Custom adapter to display the name and value of stock tickers in a mock-listview type of
 * arrangement.
 */

public class TickerAdapter extends ArrayAdapter<Ticker> {
    @BindView(R.id.ticker_name) TextView tickerName;
    @BindView(R.id.ticker_value) TextView tickerValue;

    public TickerAdapter(Context context, ArrayList<Ticker> tickers) {
        super(context, 0, tickers);
    }

    /**
     * Creates custom list view for ticker name and value
     * Guide followed at: http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#using-a-custom-arrayadapter
     */
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        // get the current item
        final Ticker tick = getItem(position);
        // check for reused view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ticker_item, parent, false);
        }

        // create objects to assign data later
        ButterKnife.bind(this, convertView);

        tickerName.setText(tick.getName());
        // set it all caps, because stocks are usually shown that way
        tickerName.setAllCaps(true);
        tickerValue.setText(String.valueOf(tick.getValue()));

        return convertView;
    }
}
