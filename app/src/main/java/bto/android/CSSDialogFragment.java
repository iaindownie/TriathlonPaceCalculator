package bto.android;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author @iaindownie on 24/09/2025.
 */

public class CSSDialogFragment extends DialogFragment {

    public static String TAG = "CSSDialogFragment";

    private Dialog alertDialog;

    private EditText css400Minutes, css400Seconds, css200Minutes, css200Seconds;
    private String css400mins, css400secs, css200mins, css200secs;
    private MaterialButton cssCalcButton;
    private TextView cssResult;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        alertDialog = new Dialog(requireContext(), R.style.FullScreenDialogStyle) {
            @Override
            public void onBackPressed() {
                alertDialog.dismiss();
            }
        };

        alertDialog.create();

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.css_dialog_fagment, null);

        ImageView close = v.findViewById(R.id.css_closer);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        cssCalcButton = v.findViewById(R.id.cssButton);
        cssResult = v.findViewById(R.id.cssResult);
        css400Minutes = v.findViewById(R.id.css400Minutes);
        css400Seconds = v.findViewById(R.id.css400Seconds);
        css200Minutes = v.findViewById(R.id.css200Minutes);
        css200Seconds = v.findViewById(R.id.css200Seconds);

        cssCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                css400mins = css400Minutes.getText().toString();
                css400secs = css400Seconds.getText().toString();
                css200mins = css200Minutes.getText().toString();
                css200secs = css200Seconds.getText().toString();

                if (css400mins == null || css400mins.isEmpty())
                    css400mins = "0";
                if (css400secs == null || css400secs.isEmpty())
                    css400secs = "0";
                if (css200mins == null || css200mins.isEmpty())
                    css200mins = "0";
                if (css200secs == null || css200secs.isEmpty())
                    css200secs = "0";

                int timeInSeconds400 = (new Integer(css400mins) * 60) + new Integer(css400secs);
                int timeInSeconds200 = (new Integer(css200mins) * 60) + new Integer(css200secs);
                double tempA = timeInSeconds400 - timeInSeconds200;
                double tempB = tempA / 2;

                cssResult.setText(String.format(getString(R.string.yourCss), returnFormattedCSS(tempB)));
            }
        });

        alertDialog.setContentView(v);
        alertDialog.show();
        return alertDialog;

    }

    private String returnFormattedCSS(double cssInSeconds) {
        if (cssInSeconds < 60.0) {
            return "0 " + getString(R.string.minsName).toLowerCase() + " " + cssInSeconds + " " + getString(R.string.secs_per_100m_or_yds);
        } else {
            double mins = Math.floor(cssInSeconds / 60.0);
            double remaining = cssInSeconds - (mins * 60);
            return (int) mins + " " + getString(R.string.minsName).toLowerCase() + " " + remaining + " " + getString(R.string.secs_per_100m_or_yds);
        }
    }

}
