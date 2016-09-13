package pip.lesson3hw;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    Button butt;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button myButton = (Button) view.findViewById(R.id.buttonClickMe);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hella Flux", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.d("MainActivityFragment", "SAD FACE");
            }
        });

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
