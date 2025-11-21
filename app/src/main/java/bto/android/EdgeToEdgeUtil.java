package bto.android;

import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * @author @iaindownie on 10/11/2025.
 */

public class EdgeToEdgeUtil {
    /**
     * Sets a listener to adjust the margins of the given root layout to accommodate system bars
     * and display cutouts. Does not consume insets.
     *
     * @param rootLayout The root {@link View} of the layout whose margins should be adjusted.
     *                   This view must have {@link ViewGroup.MarginLayoutParams}.
     * @param top        If {@code true}, the top margin will be adjusted to account for the top
     *                   system bars and display cutout.
     * @param bottom     If {@code true}, the bottom margin will be adjusted to account for the
     *                   bottom system bars and display cutout.
     */
    public static void insetEdges(View rootLayout, boolean top, boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout, (v, windowInsets) -> {
            Insets topInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.displayCutout());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            if (top) {
                mlp.topMargin = topInsets.top;
            }
            if (bottom) {
                mlp.bottomMargin = topInsets.bottom;
            }
            v.setLayoutParams(mlp);

            return windowInsets;
        });
    }

    /**
     * Adds padding to the bottom of a view to account for the navigation bar.
     * This is useful for bottom bars that should be displayed above the navigation bar. Does not
     * consume insets.
     *
     * @param bottomBar The view to pad.
     */
    public static void padBottomBar(View bottomBar) {
        ViewCompat.setOnApplyWindowInsetsListener(bottomBar, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars());
            v.setPadding(
                    v.getPaddingLeft(),
                    v.getPaddingTop(),
                    v.getPaddingRight(),
                    insets.bottom
            );
            return windowInsets;
        });
    }

    public static void padViewBottom(View view) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets innerPadding = insets.getInsets(WindowInsetsCompat.Type.navigationBars()
                    | WindowInsetsCompat.Type.ime()
            );
            v.setPadding(
                    innerPadding.left,
                    innerPadding.top,
                    innerPadding.right,
                    innerPadding.bottom
            );
            return insets;
        });
    }

    public static void requestApplyInsetsWhenAttached(View view) {
        if (view.isAttachedToWindow()) {
            view.requestApplyInsets();
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    v.removeOnAttachStateChangeListener(this);
                    v.requestApplyInsets();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                }
            });
        }
    }
}