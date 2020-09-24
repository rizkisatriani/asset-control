package com.example.barcode_counting.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class helper {
    Context context;

    public helper(Context context) {
        this.context = context;
    }

    public int getsize_screen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    public boolean HorizontalScrollViewIsScrolble(HorizontalScrollView view, LinearLayout layoutchild) {
        HorizontalScrollView scrollView = view;
        int childHeight = layoutchild.getWidth();
        boolean isScrollable = scrollView.getWidth() < childHeight + scrollView.getPaddingLeft() + scrollView.getPaddingRight();
        return isScrollable;
    }

    public void AlertYesListener(String Judul, String Pesan, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(Html.fromHtml("<b>" + Judul + "</b>"));
        alertDialogBuilder
                .setMessage(Html.fromHtml(Pesan))
                .setCancelable(false)
                .setPositiveButton("Ya",
                        listener).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void AlertNotif(String Judul, String Pesan) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(Html.fromHtml("<b>" + Judul + "</b>"));
        alertDialogBuilder
                .setMessage(Html.fromHtml(Pesan))
                .setCancelable(false)
                .setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
