package com.sunil.rxretrofitapp.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.sunil.rxretrofitapp.R;

/**
 * Created by sunil on 26-Sep-16.
 */

public class DialogFactory {

    public static ProgressDialog mProgressDialog = null;

    public static void displayProgressDialog(Context context, String message) {
        if (mProgressDialog == null && context != null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(message);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
        }
    }

    public static void cancelProgressDialog() {
        if (mProgressDialog != null) {
            try {
                mProgressDialog.dismiss();
                mProgressDialog.cancel();
                mProgressDialog = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   @StringRes int titleResource,
                                                   @StringRes int messageResource) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }

    public static Dialog createGenericErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
        return createGenericErrorDialog(context, context.getString(messageResource));
    }

    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context,
                                                      @StringRes int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }
}
