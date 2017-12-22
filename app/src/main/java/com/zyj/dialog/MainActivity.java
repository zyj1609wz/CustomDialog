package com.zyj.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zyj.dialog.service.MyService;
import com.zyj.dialog.view.WiFiConnectDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConnectDialog();
            }
        });

        //启动Service
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                getApplicationContext().startService(intent);
            }
        });

    }

    void showConnectDialog() {
        WiFiConnectDialog.Builder builder = new WiFiConnectDialog.Builder(MainActivity.this);
        builder.setSsid("yibaif");
        builder.setNegativeButton(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //连接
                Toast.makeText(MainActivity.this, "连接了", Toast.LENGTH_SHORT).show();
            }
        });

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);   //触摸dialog外部是否消失
        dialog.show();
    }
}
