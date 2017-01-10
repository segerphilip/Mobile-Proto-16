package pip.lesson3HW;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * INCLASS work on fragments
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button myButton1 = (Button) view.findViewById(R.id.fragmentButton);
        Button myButton2 = (Button) view.findViewById(R.id.fragmentButtonClick);
        final TextView text = (TextView) view.findViewById(R.id.fragmentTextView);

        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivityFragment", "Boring print statements in logs are boring");
                text.setText("So long suckas");
            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // What do I even do with you
                text.setText("Hello agaWAIT WHAT?! I\'M STUCK IN AN APP!!!");
            }
        });

        return view;
    }
}
