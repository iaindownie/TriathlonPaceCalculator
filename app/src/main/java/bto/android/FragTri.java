package bto.android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

/**
 * @author @iaindownie on 05/02/2026.
 *
 * Based on one users request
 */

public class FragTri extends Fragment implements View.OnClickListener {

    public FragTri() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_bike, container, false);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        // do what you want to do when button is clicked
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
