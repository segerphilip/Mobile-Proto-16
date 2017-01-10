package pip.lesson3HW;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment handling the settings to change background color
 */
public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        // Get root view to be able to set background color
        final View rootView = getActivity().getWindow().getDecorView();

        // Create buttons for changing background color
        Button myButton1 = (Button) view.findViewById(R.id.settingsButton1);
        Button myButton2 = (Button) view.findViewById(R.id.settingsButton2);
        Button myButton3 = (Button) view.findViewById(R.id.settingsButton3);

        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SettingsFragment", "button 1 clicked");
                // You used the deprecated getColor. I fixed it. Check this stackoverflow
                // http://stackoverflow.com/questions/31590714/getcolorint-id-deprecated-on-android-6-0-marshmallow-api-23
                // Set rootView background color
                rootView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SettingsFragment", "button 2 clicked");
                rootView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            }
        });

        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SettingsFragment", "button 3 clicked");
                rootView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWrong));
            }
        });

        return view;
    }
}
