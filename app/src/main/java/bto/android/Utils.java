package bto.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import it.sephiroth.android.library.tooltip.Tooltip;

/**
 * @author @iaindownie on 08/07/2025.
 */

public class Utils {

    public static void doAboutDialog(Activity activity) {
        PackageInfo pInfo = null;
        try {
            pInfo = activity.getPackageManager().getPackageInfo("bto.android",
                    PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer spawny = new StringBuffer();
        spawny.append(activity.getResources().getString(R.string.about_text));

        new MaterialAlertDialogBuilder(activity, R.style.AlertDialogTheme)
                .setTitle(activity.getString(R.string.about_title) + " " + pInfo.versionName)
                .setMessage(spawny.toString())
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    public static void doHelpDialog(Activity activity) {
        StringBuffer sb = new StringBuffer();
        sb.append(activity.getResources().getString(R.string.help_content1));
        sb.append(activity.getResources().getString(R.string.help_content2));

        new MaterialAlertDialogBuilder(activity, R.style.AlertDialogTheme)
                .setTitle(R.string.how_to_use)
                .setMessage(sb.toString())
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }


    public static MaterialButton returnStyledButton(Activity activity, MaterialButton button, boolean selected) {
        if (selected) {
            button.setBackgroundColor(activity.getResources().getColor(R.color.base_color_info_default));
            button.setTextColor(activity.getResources().getColor(R.color.white));
        } else {
            button.setBackgroundColor(activity.getResources().getColor(R.color.white));
            button.setTextColor(activity.getResources().getColor(R.color.base_color_neutral_default));
        }
        return button;
    }

    public static void styleButton(Activity activity, MaterialButton button, boolean selected) {
        if (selected) {
            button.setBackgroundColor(activity.getResources().getColor(R.color.base_color_info_default));
            button.setTextColor(activity.getResources().getColor(R.color.white));
        } else {
            button.setBackgroundColor(activity.getResources().getColor(R.color.white));
            button.setTextColor(activity.getResources().getColor(R.color.base_color_neutral_default));
        }
    }

    public static void handleBlueAlert(Activity activity, View view, Tooltip.Gravity gravity, String string) {

        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();

        Tooltip.make(activity,
                new Tooltip.Builder(101)
                        .anchor(view, gravity)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 10000)
                        .text(string)
                        .maxWidth(metrics.widthPixels / 2)
                        .withStyleId(R.style.ToolTipLayout8RadStyle)
                        .typeface(Typeface.DEFAULT_BOLD)
                        .withArrow(true)
                        .withOverlay(true)
                        .build()
        ).show();

    }

    public static void openURL(String url, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }

    public static void doPredictionInfoDialog(Activity activity) {
        StringBuffer sb = new StringBuffer();
        sb.append(activity.getResources().getString(R.string.predicted_race_performance_text1));
        sb.append(activity.getResources().getString(R.string.predicted_race_performance_text2));

        new MaterialAlertDialogBuilder(activity, R.style.AlertDialogTheme)
                .setTitle(R.string.predicted_race_performance)
                .setMessage(sb.toString())
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    public static String getGoodTimeEndValues(double val) {
        val = val / 60;
        int mins = (int) val;
        double secs = val - mins;
        if (mins >= 60) {
            int hours = mins / 60;
            String str = (hours + ":" + (paddedInt((mins - (hours * 60))))
                    + ":" + paddedInt((int) Math.round(secs * 60)));
            return str;
        } else {
            return "0:" + paddedInt(mins) + ":"
                    + paddedInt((int) Math.round(secs * 60));
        }
    }

    public static String paddedInt(int val) {
        if (val < 10)
            return "0" + val;
        else
            return "" + val;
    }

    public static String getTimeFromThreeTextViews(double hours, double mins, double secs) {
        double total = 0.0;
        if (hours > 0) {
            total = ((hours * 60) * 60 * mins) + secs;
        } else {
            total = (60 * mins) + secs;
        }
        int tHours = (int) (total / 60 / 60);
        int tMins = (int) ((total / 60) - (tHours * 60));
        double tSecs = (double) (total - ((tHours * 60 * 60) + (tMins * 60)));

        return Utils.paddedInt(tHours) + ":" + Utils.paddedInt(tMins) + ":" + tSecs;
    }

    public static String handleZeroStringValues(String str) {
        if (str.isEmpty()) {
            return "0";
        } else {
            return str;
        }
    }
}
