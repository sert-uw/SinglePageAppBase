package com.example.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
import android.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
  public static MyDialogFragment newInstance(String[] params) {

    MyDialogFragment instance = new MyDialogFragment();

    Bundle arguments = new Bundle();
    arguments.putString("title", params[0]);
    arguments.putString("message", params[1]);
    arguments.putString("posStr", params[2]);
    arguments.putString("neuStr", params[3]);
    arguments.putString("negStr", params[4]);

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

    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
      .setTitle(title)
      .setMessage(message)
      .setPositiveButton(posStr, null);

    if(!neuStr.equals(""))
      alert.setNeutralButton(neuStr, null);

    if(!negStr.equals(""))
      alert.setNegativeButton(negStr, null);

    return alert.create();
  }
}
