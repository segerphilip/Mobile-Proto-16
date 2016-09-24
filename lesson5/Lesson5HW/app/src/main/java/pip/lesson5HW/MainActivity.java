package pip.lesson5HW;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Main activity which loads either a to-do fragment, or changes to settings fragment
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // shared preferences to save background color values
        SharedPreferences sharedPref = getSharedPreferences(getString(
                R.string.sharedPrefs), Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.defaultBackground);
        String background = sharedPref.getString(getString(R.string.savedBackground), defaultValue);
        // set the background based on either default or previously saved color
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(background));

        // create to-do items fragment and add it to current activity
        final NotesFragment newFragment = new NotesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment, "notesFragment");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu; used to have settings which leads to background change buttons
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle action bar item clicks here
        int id = item.getItemId();

        // when settings dropdown clicked, switch current fragment to settings fragment
        if (id == R.id.action_settings) {
            SettingsFragment newFragment = new SettingsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, newFragment, "settingsFragment");
            transaction.addToBackStack(null);
            transaction.commit();
        }

        if (id == R.id.action_remove) {
            NotesFragment loadedFragment = (NotesFragment) getSupportFragmentManager().findFragmentByTag("notesFragment");
            loadedFragment.clearDone();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to save the background color using shared preferences, called from SettingsFragment
     */
    public void editPrefs(int color) {
        SharedPreferences sharedPref = getSharedPreferences(getString(
                R.string.sharedPrefs), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.savedBackground), getString(color));
        // apply writes data in background, non-blocking which is nice
        editor.apply();
    }
}
