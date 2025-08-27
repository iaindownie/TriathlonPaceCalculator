package bto.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import it.sephiroth.android.library.tooltip.Tooltip;

public class MainActivity extends AppCompatActivity {

    private boolean isDarkModeEnabled = false;

    private Activity activity;
    private Integer tabPref;
    private SharedPreferences prefs;

    private MaterialButtonToggleGroup materialButtonToggleGroup;
    private MaterialButton button1, button2, button3;
    private FrameLayout f1, f2, f3;

    private FragRun fragRun;
    private FragBike fragBike;
    private FragSwim fragSwim;
    private TextView topLine;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        activity = this;
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        // Unused for now
        prefs = getPreferences(Context.MODE_PRIVATE);
        tabPref = prefs.getInt("tabPref", 0);
        //Log.d("INFO", "TabPref: " + tabPref);

        // Help line above fields
        topLine = findViewById(R.id.toplineInfo);

        // Set up the fragment containers
        f1 = findViewById(R.id.fragment1);
        f2 = findViewById(R.id.fragment2);
        f3 = findViewById(R.id.fragment3);

        // Toggle buttons to switch between fragments
        materialButtonToggleGroup =
                findViewById(R.id.toggleButton);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        // Set run selected by default on launch
        button1 = Utils.returnStyledButton(activity, button1, true);

        // Add the 3 activity type fragments to the containers
        FragmentManager manager = getSupportFragmentManager();
        fragRun = new FragRun();
        fragBike = new FragBike();
        fragSwim = new FragSwim();
        manager.beginTransaction().replace(R.id.fragment1, fragRun, "fragmentone").commit();
        manager.beginTransaction().replace(R.id.fragment2, fragBike, "fragmenttwo").commit();
        manager.beginTransaction().replace(R.id.fragment3, fragSwim, "fragmentthree").commit();

        if (tabPref == 0) {
            button1 = Utils.returnStyledButton(activity, button1, true);
            button2 = Utils.returnStyledButton(activity, button2, false);
            button3 = Utils.returnStyledButton(activity, button3, false);
            f1.setVisibility(View.VISIBLE);
            f2.setVisibility(View.GONE);
            f3.setVisibility(View.GONE);
        } else if (tabPref == 1) {
            button1 = Utils.returnStyledButton(activity, button1, false);
            button2 = Utils.returnStyledButton(activity, button2, true);
            button3 = Utils.returnStyledButton(activity, button3, false);
            f1.setVisibility(View.GONE);
            f2.setVisibility(View.VISIBLE);
            f3.setVisibility(View.GONE);
        } else {
            button1 = Utils.returnStyledButton(activity, button1, false);
            button2 = Utils.returnStyledButton(activity, button2, false);
            button3 = Utils.returnStyledButton(activity, button3, true);
            f1.setVisibility(View.GONE);
            f2.setVisibility(View.GONE);
            f3.setVisibility(View.VISIBLE);
        }

        boolean tutorialBlueBubble = prefs.getBoolean("TUTORIAL_BLUE_BUBBLE", false);
        if (!tutorialBlueBubble) {
            Utils.handleBlueAlert(activity, button2, Tooltip.Gravity.BOTTOM, getString(R.string.tutorial_blue_bubble));

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("TUTORIAL_BLUE_BUBBLE", true);
            editor.apply();
        }


        // Set up toggle button listener
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == button1.getId()) {
                        f1.setVisibility(View.VISIBLE);
                        f2.setVisibility(View.GONE);
                        f3.setVisibility(View.GONE);
                        button1 = Utils.returnStyledButton(activity, button1, true);
                        button2 = Utils.returnStyledButton(activity, button2, false);
                        button3 = Utils.returnStyledButton(activity, button3, false);
                        topLine.setText(R.string.helpTextGeneric);

                        SharedPreferences.Editor editor = prefs.edit().putInt(
                                "tabPref", 0);
                        editor.apply();
                        mFirebaseAnalytics.logEvent("Run_Clicked", new Bundle());
                    }
                    if (checkedId == button2.getId()) {
                        f1.setVisibility(View.GONE);
                        f2.setVisibility(View.VISIBLE);
                        f3.setVisibility(View.GONE);
                        button1 = Utils.returnStyledButton(activity, button1, false);
                        button2 = Utils.returnStyledButton(activity, button2, true);
                        button3 = Utils.returnStyledButton(activity, button3, false);
                        topLine.setText(R.string.helpTextBike);

                        SharedPreferences.Editor editor = prefs.edit().putInt(
                                "tabPref", 1);
                        editor.apply();
                        mFirebaseAnalytics.logEvent("Bike_Clicked", new Bundle());
                    }
                    if (checkedId == button3.getId()) {
                        f1.setVisibility(View.GONE);
                        f2.setVisibility(View.GONE);
                        f3.setVisibility(View.VISIBLE);
                        button1 = Utils.returnStyledButton(activity, button1, false);
                        button2 = Utils.returnStyledButton(activity, button2, false);
                        button3 = Utils.returnStyledButton(activity, button3, true);
                        topLine.setText(R.string.helpTextGeneric);

                        SharedPreferences.Editor editor = prefs.edit().putInt(
                                "tabPref", 2);
                        editor.apply();
                        mFirebaseAnalytics.logEvent("Swim_Clicked", new Bundle());
                    }
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.about) {
            Utils.doAboutDialog(this);
            mFirebaseAnalytics.logEvent("About_Clicked", new Bundle());
            return true;
        }
        if (id == R.id.instructions) {
            Utils.doHelpDialog(activity);
            mFirebaseAnalytics.logEvent("Help_Clicked", new Bundle());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


