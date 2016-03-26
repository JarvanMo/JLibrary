package com.jarvanmo.jlibrary.update;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.jarvanmo.jlibrary.R;


public class UpdateDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.new_version_available);
        builder.setMessage(getArguments().getString(UpdateChecker.APK_UPDATE_CONTENT))
                .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        goToDownload();
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    private void goToDownload() {
        Intent intent = new Intent(getActivity().getApplicationContext(), DownloadService.class);
        intent.putExtra(UpdateChecker.APK_DOWNLOAD_URL, getArguments().getString(UpdateChecker.APK_DOWNLOAD_URL));
        getActivity().startService(intent);
    }
}
