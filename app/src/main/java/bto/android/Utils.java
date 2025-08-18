package bto.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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

}
