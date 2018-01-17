package com.holding.medis.views.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.holding.medis.R;
import com.holding.medis.UserConfig;
import com.holding.medis.tools.UserTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by hosein on 2/4/2017.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        UserConfig.getInstance().init(this, UserTools.getInstance().getLanguageType());
    }


    protected ProgressDialog mProgressDialog;
    protected AlertDialog.Builder mAlertDialog;


    protected void needShowProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getResources().getText(R.string.please_wait));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    protected void needHideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void needShowAlertDialog(String message) {
        mAlertDialog = new AlertDialog.Builder(this);
        mAlertDialog.setTitle(getResources().getText(R.string.app_name));
        mAlertDialog.setMessage(message);
        mAlertDialog.setPositiveButton(getResources().getText(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = mAlertDialog.create();
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        Button button1 = (Button) dialog.findViewById(android.R.id.button1);
        Button button2 = (Button) dialog.findViewById(android.R.id.button2);

        button1.setTextSize(14);
        button2.setTextSize(14);
        textView.setTextSize(14);

    }

    protected void needShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void needShowSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void needShareApplication(Activity paramActivity, String paramStringToastMsg) throws IOException {
        ApplicationInfo app = paramActivity.getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location
            File tempFile = new File(paramActivity.getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/"  + "cafebook.apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            //Open share dialog
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            paramActivity.startActivity(Intent.createChooser(intent, paramActivity.getResources().getText(R.string.app_name)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void shareFile(String filePath) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri contentUri;
        File file = new File(filePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sharingIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            sharingIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            sharingIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            contentUri = FileProvider.getUriForFile(this, "com.cafe_book.cafebook.fileProvider", file);
        } else {
            contentUri = Uri.fromFile(file);
        }
        sharingIntent.setType("*/*");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        startActivity(Intent.createChooser(sharingIntent, getResources().getText(R.string.app_name)));

    }

    @Override
    public void onStop() {
        super.onStop();
        needHideProgressDialog();
    }
}
