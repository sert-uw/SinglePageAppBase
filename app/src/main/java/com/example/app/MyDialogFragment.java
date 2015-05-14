package com.example.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    private DialogListener listener = null;

    public static MyDialogFragment newInstance(String[] params) {

        MyDialogFragment instance = new MyDialogFragment();

        Bundle arguments = new Bundle();
        arguments.putString("title", params[0]);
        arguments.putString("message", params[1]);
        arguments.putString("posStr", params[2]);
        arguments.putString("neuStr", params[3]);
        arguments.putString("negStr", params[4]);
        arguments.putString("posJs", params[5]);
        arguments.putString("neuJs", params[6]);
        arguments.putString("negJs", params[7]);

        instance.setArguments(arguments);

        return instance;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        String posStr = getArguments().getString("posStr");
        String neuStr = getArguments().getString("neuStr");
        String negStr = getArguments().getString("negStr");

        final String posJs = getArguments().getString("posJs");
        final String neuJs = getArguments().getString("neuJs");
        final String negJs = getArguments().getString("negJs");

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(posStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.doClick(posJs);
                        dismiss();
                    }
                });

        if(!neuStr.equals(""))
            alert.setNeutralButton(neuStr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.doClick(neuJs);
                    dismiss();
                }
            });

        if(!negStr.equals(""))
            alert.setNegativeButton(negStr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.doClick(negJs);
                    dismiss();
                }
            });

        return alert.create();
    }

    public void setDialogListener(DialogListener listener){
        this.listener = listener;
    }
}
