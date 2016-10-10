package com.duanjunxiao.commondialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongDialog(View view) {
        new CommonDialog2.Builder(this)
                .setIsShortDialog(false)
                .setCancelable(false)
                .setTitle("起风了")
                .setMessage(R.string.dialog_text)
                .setPositiveButton(R.string.action_know, null)
                .create().show();
    }

    public void showShortDialog(View view) {
        new CommonDialog2.Builder(this)
                .setTitle("自定义Dialog")
                .setMessage("自定义Dialog之-建造者模式Builder")
                .setCancelable(false)
                .setPositiveButton(R.string.action_delete, new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        showMessage(R.string.action_delete);
                    }
                })
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        showMessage(R.string.action_cancel);
                    }
                }).create().show();
    }

    public void showShortDialogRed(View view) {
        new CommonDialog2.Builder(this)
                .setTitle("自定义Dialog")
                .setMessage("自定义Dialog之-建造者模式Builder")
                .setPositiveButton(R.string.action_delete, new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        showMessage(R.string.action_delete);
                    }
                }, R.drawable.common_red_corner_selector)
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        showMessage(R.string.action_cancel);
                    }
                }).create().show();
    }


}
