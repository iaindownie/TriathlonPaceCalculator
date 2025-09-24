package bto.android;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author @iaindownie on 24/09/2025.
 */

public class CSSDialogFragment extends DialogFragment {

    public static String TAG = "CSSDialogFragment";

    private Dialog alertDialog;

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


        alertDialog.setContentView(v);
        alertDialog.show();
        return alertDialog;

    }

}
