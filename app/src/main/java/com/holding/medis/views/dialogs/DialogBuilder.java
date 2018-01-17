package com.holding.medis.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.TextView;

import com.holding.medis.R;


public class DialogBuilder {

    private Context context;
    private String message;
    private boolean isNegativeButton;
    private boolean canelable = true;
    private OnClickListener onClickListener;

    public interface OnClickListener {
        void onClickPositiveButton(DialogInterface dialog, int which);

        void onClickNegativeButton(DialogInterface dialog, int which);
    }

    public DialogBuilder(Context context, String message, boolean isNegativeButton, boolean canelable) {
        this.context = context;
        this.message = message;
        this.isNegativeButton = isNegativeButton;
        this.canelable = canelable;
    }

    public DialogBuilder(Context context, String message, boolean isNegativeButton) {
        this.context = context;
        this.message = message;
        this.isNegativeButton = isNegativeButton;
    }

    public DialogBuilder(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    public DialogBuilder setListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public void show() {
        if (context == null) {
            return;
        }
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(context);
        mAlertDialog.setTitle(context.getResources().getText(R.string.app_name));
        mAlertDialog.setCancelable(canelable);
        mAlertDialog.setMessage(message);
        mAlertDialog.setPositiveButton(context.getResources().getText(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null) {
                    onClickListener.onClickPositiveButton(dialog, which);
                    return;
                }
            }
        });

        if (isNegativeButton) {
            mAlertDialog.setNegativeButton(context.getResources().getText(R.string.cancell), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (onClickListener != null) {
                        onClickListener.onClickNegativeButton(dialog, which);
                        return;
                    }
                }
            });
        }
        AlertDialog dialog = mAlertDialog.create();
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        Button button1 = (Button) dialog.findViewById(android.R.id.button1);
        Button button2 = (Button) dialog.findViewById(android.R.id.button2);

        button1.setTextSize(14);
        button2.setTextSize(14);
        textView.setTextSize(14);

    }
}
