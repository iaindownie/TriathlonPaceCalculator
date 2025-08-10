package bto.android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bto.android.adapters.ResultsRecycler;

public class FragSwim extends Fragment implements View.OnClickListener {

    private EditText text1a;
    private EditText text1b;
    private EditText text1c;
    private EditText text2;
    private EditText text3b;
    private EditText text3c;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private RecyclerView recyclerView;
    private Button clearButton;
    private Button timeButton;
    private Button distanceButton;
    private Button paceButton;
    private TextView filler3swim;
    private ToggleButton toggle;
    private boolean isMetric;

    public FragSwim() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_swim, container, false);

        text1a = (EditText) rootView.findViewById(R.id.EditText01a);
        text1b = (EditText) rootView.findViewById(R.id.EditText01b);
        text1c = (EditText) rootView.findViewById(R.id.EditText01c);
        text2 = (EditText) rootView.findViewById(R.id.EditText02);
        text3b = (EditText) rootView.findViewById(R.id.EditText03b);
        text3c = (EditText) rootView.findViewById(R.id.EditText03c);
        text1a.setWidth(10);
        text1b.setWidth(10);
        text1c.setWidth(10);
        recyclerView = rootView.findViewById(R.id.ListViewSwim);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        clearButton = (Button) rootView.findViewById(R.id.ClearButton);
        clearButton.setOnClickListener(this);
        timeButton = (Button) rootView.findViewById(R.id.Button01);
        timeButton.setOnClickListener(this);
        distanceButton = (Button) rootView.findViewById(R.id.Button02);
        distanceButton.setOnClickListener(this);
        paceButton = (Button) rootView.findViewById(R.id.Button03);
        paceButton.setOnClickListener(this);
        filler3swim = (TextView) rootView.findViewById(R.id.filler3swim);
        filler3swim.setText(R.string.per100m);

        spinner = (Spinner) rootView.findViewById(R.id.spinnerswim);
        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.metricSwim, android.R.layout.simple_spinner_item);
        isMetric = true;
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int selectedPosition, long arg3) {

                if (selectedPosition == 0) {
                    text2.setText("");
                    recyclerView.setAdapter(null);
                } else {
                    String str = getPresetDistance(selectedPosition, isMetric);
                    text2.setText(str);
                    distanceButton.setEnabled(false);
                    text2.setFocusable(true);
                    text2.setFocusableInTouchMode(true);
                    text2.requestFocus();
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        toggle = (ToggleButton) rootView.findViewById(R.id.togglebuttonSwim);
        toggle.setChecked(true);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    text2.setHint(getString(R.string.metres));
                    adapter = ArrayAdapter.createFromResource(getActivity(),
                            R.array.metricSwim,
                            android.R.layout.simple_spinner_item);
                    isMetric = true;
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

                        public void onItemSelected(AdapterView<?> arg0,
                                                   View arg1, int selectedPosition, long arg3) {

                            if (selectedPosition == 0) {
                                text2.setText("");
                                recyclerView.setAdapter(null);
                            } else {
                                String str = getPresetDistance(
                                        selectedPosition, isMetric);
                                text2.setText(str);
                                distanceButton.setEnabled(false);
                                text2.setFocusable(true);
                                text2.setFocusableInTouchMode(true);
                                text2.requestFocus();
                            }
                        }

                        public void onNothingSelected(AdapterView<?> arg0) {
                        }
                    });
                } else {
                    // The toggle is disabled
                    text2.setHint(getString(R.string.yards));
                    adapter = ArrayAdapter.createFromResource(getActivity(),
                            R.array.imperialSwim,
                            android.R.layout.simple_spinner_item);
                    isMetric = false;
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

                        public void onItemSelected(AdapterView<?> arg0,
                                                   View arg1, int selectedPosition, long arg3) {

                            if (selectedPosition == 0) {
                                text2.setText("");
                                recyclerView.setAdapter(null);
                            } else {
                                String str = getPresetDistance(
                                        selectedPosition, isMetric);
                                text2.setText(str);
                                distanceButton.setEnabled(false);
                                text2.setFocusable(true);
                                text2.setFocusableInTouchMode(true);
                                text2.requestFocus();
                            }
                        }

                        public void onNothingSelected(AdapterView<?> arg0) {
                        }
                    });
                }
            }
        });

        text1a.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timeButton.setEnabled(false);
                } else {
                    timeButton.setEnabled(true);
                }
            }
        });
        text1b.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timeButton.setEnabled(false);
                } else {
                    timeButton.setEnabled(true);
                }
            }
        });
        text1c.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timeButton.setEnabled(false);
                } else {
                    timeButton.setEnabled(true);
                }
            }
        });
        text2.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    distanceButton.setEnabled(false);
                } else {
                    distanceButton.setEnabled(true);
                }
            }
        });

        text3b.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    paceButton.setEnabled(false);
                } else {
                    paceButton.setEnabled(true);
                }
            }
        });
        text3c.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    paceButton.setEnabled(false);
                } else {
                    paceButton.setEnabled(true);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        // do what you want to do when button is clicked
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (v.getId() == timeButton.getId()) {
            String t3b = text3b.getText().toString();
            if (t3b == null || t3b.length() == 0)
                t3b = "0";
            String t3c = text3c.getText().toString();
            if (t3c == null || t3c.length() == 0)
                t3c = "0";
            String d2 = text2.getText().toString();
            if (d2 == null || d2.length() == 0)
                d2 = "0";
            String time = getTime(Double.valueOf(d2), Double.valueOf(t3b),
                    Double.valueOf(t3c));
            text1a.setText(time.substring(0, time.indexOf(":")));
            text1b.setText(time.substring(time.indexOf(":") + 1,
                    time.lastIndexOf(":")));
            text1c.setText(time.substring(time.lastIndexOf(":") + 1));
            imm.hideSoftInputFromWindow(text3c.getWindowToken(), 0);
        }
        if (v.getId() == distanceButton.getId()) {
            String aaa = text1a.getText().toString();
            if (aaa == null || aaa.length() == 0)
                aaa = "0";
            String bbb = text1b.getText().toString();
            if (bbb == null || bbb.length() == 0)
                bbb = "0";
            String ccc = text1c.getText().toString();
            if (ccc == null || ccc.length() == 0)
                ccc = "0";
            String eee = text3b.getText().toString();
            if (eee == null || eee.length() == 0)
                eee = "0";
            String fff = text3c.getText().toString();
            if (fff == null || fff.length() == 0)
                fff = "0";
            String dist = getDistance(Double.valueOf(aaa), Double.valueOf(bbb),
                    Double.valueOf(ccc), Double.valueOf(eee),
                    Double.valueOf(fff));
            text2.setText(dist);
            imm.hideSoftInputFromWindow(text1c.getWindowToken(), 0);
        }
        if (v.getId() == paceButton.getId()) {
            String t1a = text1a.getText().toString();
            if (t1a == null || t1a.length() == 0)
                t1a = "0";
            String t1b = text1b.getText().toString();
            if (t1b == null || t1b.length() == 0)
                t1b = "0";
            String t1c = text1c.getText().toString();
            if (t1c == null || t1c.length() == 0)
                t1c = "0";
            String d3 = text2.getText().toString();
            if (d3 == null || d3.length() == 0)
                d3 = "0";
            String pace = getPace(Double.valueOf(d3), Double.valueOf(t1a),
                    Double.valueOf(t1b), Double.valueOf(t1c));
            text3b.setText(pace.substring(0, pace.indexOf(":")));
            text3c.setText(pace.substring(pace.indexOf(":") + 1));
            if (isMetric) {
                filler3swim.setText("/100m");
            } else {
                filler3swim.setText("/100yd");
            }
            imm.hideSoftInputFromWindow(text2.getWindowToken(), 0);
        }
        if (v.getId() == clearButton.getId()) {
            text1a.setText("");
            text1b.setText("");
            text1c.setText("");
            text2.setText("");
            spinner.setSelection(0);
            text3b.setText("");
            text3c.setText("");
            recyclerView.setAdapter(null);
            imm.hideSoftInputFromWindow(text2.getWindowToken(), 0);
        }
        timeButton.setEnabled(true);
        distanceButton.setEnabled(true);
        paceButton.setEnabled(true);
    }


    public void setSplits(double dist, double total) {
        ArrayList<String> results = new ArrayList<String>();
        results.add("Conversion summary");
        double pace = (total / (dist / 100)) / 60;
        if (isMetric) {
            results.add("Pace: " + getGoodTimeValues(pace) + " /100m");
            results.add("Speed: " + getSpeed(dist, total, isMetric) + " kmh");
        } else {
            results.add("Pace: " + getGoodTimeValues(pace) + " /100yd");
            results.add("Speed: " + getSpeed(dist, total, isMetric) + " mph");
        }
        ResultsRecycler rr = new ResultsRecycler(results);
        recyclerView.setAdapter(rr);
    }

    private String getSpeed(Double dist, Double total, boolean metric) {
        double speed = (dist / 1000.0)
                / Constants.convertSeconds2DecimalHours(dist, total);
        if (metric) {
            return "" + Constants.round(speed, 2);
        } else {
            return "" + Constants.round(speed / Constants.toKmConversion, 2);
        }
    }

    private String getGoodTimeValues(double val) {
        int mins = (int) val;
        double secs = val - mins;
        if (mins >= 60) {
            int hours = mins / 60;
            String str = ((paddedInt((mins - (hours * 60)))) + ":" + paddedInt((int) Math
                    .round(secs * 60)));
            return str;
        } else {
            return paddedInt(mins) + ":"
                    + paddedInt((int) Math.round(secs * 60));
        }
    }

    private String getTime(Double dist, Double mins, Double secs) {
        double total = 0.0;
        total = (dist / 100) * ((60 * mins) + secs);
        int tHours = (int) (total / 60 / 60);
        int tMins = (int) ((total / 60) - (tHours * 60));
        double tSecs = (double) (total - ((tHours * 60 * 60) + (tMins * 60)));
        this.setSplits(dist.doubleValue(), total);
        return paddedInt(tHours) + ":" + paddedInt(tMins) + ":" + tSecs;
    }

    private String getPace(Double dist, Double hours, Double mins, Double secs) {
        double totalSecs = 0.0;
        if (hours > 0) {
            totalSecs = (hours * 60 * 60) + (60 * mins) + secs;
        } else {
            totalSecs = (60 * mins) + secs;
        }
        double total = totalSecs / (dist / 100);
        int tHours = (int) (total / 60 / 60);
        int tMins = (int) ((total / 60) - (tHours * 60));
        Double tSecs = Double
                .valueOf((total - ((tHours * 60 * 60) + (tMins * 60))));
        if (!tSecs.isNaN()) {
            this.setSplits(dist.doubleValue(), totalSecs);
            return paddedInt(tMins) + ":" + tSecs.doubleValue();
        } else {
            return "00:0.0";
        }
    }

    private String getDistance(Double hours1, Double mins1, Double secs1,
                               Double mins2, Double secs2) {
        double totalSecs1 = 0.0;
        if (hours1 > 0) {
            totalSecs1 = (hours1 * 60 * 60) + (60 * mins1) + secs1;
        } else {
            totalSecs1 = (60 * mins1) + secs1;
        }
        double totalSecs2 = 0.0;
        totalSecs2 = (60 * mins2) + secs2;
        if (totalSecs1 > 0.0 && totalSecs2 > 0.0) {
            this.setSplits(totalSecs1 / totalSecs2, totalSecs1);
            return "" + Constants.round(((totalSecs1 / totalSecs2) * 100), 2);
        } else
            return "0.0";
    }

    private String paddedInt(int val) {
        if (val < 10)
            return "0" + val;
        else
            return "" + val;
    }

    private String getPresetDistance(int preset, boolean isMetric) {
        if (isMetric) {
            if (preset == 1)
                return "10000";
            else if (preset == 2)
                return "3800";
            else if (preset == 3)
                return "1900";
            else if (preset == 4)
                return "1500";
            else if (preset == 5)
                return "800";
            else if (preset == 6)
                return "400";
            else if (preset == 7)
                return "300";
            else if (preset == 8)
                return "200";
            else if (preset == 9)
                return "100";
            else if (preset == 10)
                return "50";
            else
                return "";
        } else {
            if (preset == 1)
                return "10936";
            else if (preset == 2)
                return "4155";
            else if (preset == 3)
                return "2077";
            else if (preset == 4)
                return "1640";
            else if (preset == 5)
                return "875";
            else if (preset == 6)
                return "437";
            else if (preset == 7)
                return "219";
            else if (preset == 8)
                return "109";
            else if (preset == 9)
                return "100";
            else if (preset == 10)
                return "55";
            else
                return "";
        }
    }

}