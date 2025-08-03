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
         //System.out.println("IAIN:" + getApplicationContext().getPackageName());
         /*
          * pInfo = getPackageManager().getPackageInfo("org.spawny.duathlon",
          * PackageManager.GET_META_DATA);
          */
      } catch (PackageManager.NameNotFoundException e) {
         e.printStackTrace();
      }

      StringBuffer spawny = new StringBuffer();
      spawny.append("A simple app to help runners, cyclists and swimmers calculate ");
      spawny.append("pace or speed, and help make predictions for future events.\n\n");
      spawny.append("This is a Spawny App!");

      new MaterialAlertDialogBuilder(activity, R.style.AlertDialogTheme)
              .setTitle(activity.getString(R.string.about_title) + pInfo.versionName)
              .setMessage(spawny.toString())
              .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                 }
              })
              .show();

//      MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(activity);
//      View v = View.inflate(activity, R.layout.about, null);
//      TextView text = v.findViewById(R.id.text);

//      text.setText(spawny.toString());
//
//      Button but = v.findViewById(R.id.dismissButton);
//      but.setOnClickListener(new View.OnClickListener() {
//         // @Override
//         public void onClick(View v) {
//            dialog.c
//         }
//      });
//      dialog.setView(v);
//      dialog.setTitle("Run/Bike Calculator " + pInfo.versionName);
//      dialog.show();
   }

//   private void doHelpDialog() {
//      final Dialog dialog = new Dialog(this);
//      dialog.setContentView(R.layout.instructions);
//      dialog.setTitle("Instructions");
//      /*
//       * ImageView image = (ImageView) dialog.findViewById(R.id.image);
//       * image.setImageResource(R.drawable.perfcoach_banner);
//       * image.setOnClickListener(new View.OnClickListener() { public void
//       * onClick(View v) { Intent intent = new Intent();
//       * intent.setAction(Intent.ACTION_VIEW);
//       * intent.addCategory(Intent.CATEGORY_BROWSABLE);
//       * intent.setData(Uri.parse("http://www.performancecoaching.me/"));
//       * startActivity(intent); } });
//       */
//      Button but = (Button) dialog.findViewById(R.id.helpDismissButton);
//      dialog.show();
//      but.setOnClickListener(new OnClickListener() {
//         // @Override
//         public void onClick(View v) {
//            dialog.dismiss();
//         }
//      });
//   }

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
