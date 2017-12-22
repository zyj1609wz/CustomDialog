package com.zyj.dialog.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zyj.dialog.MainActivity;

public class MyService extends Service {

    private ServiceDialog serviceDialog;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 显示dialog
     *
     * @param ssid
     */
    public void showDialog(String ssid) {
        if (serviceDialog != null && serviceDialog.isShowing()) {
            //如果旧的dialog 存在，就直接更新wifi 名字
            serviceDialog.updateWifiName(ssid);
            return;
        }
        serviceDialog = new ServiceDialog(this);
        serviceDialog.setOnEventListener(new ServiceDialog.OnEventListener() {
            @Override
            public void onSure() {
                serviceDialog.dismiss();
                Intent intent = new Intent(MyService.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                serviceDialog.dismiss();
            }
        });

        serviceDialog.setCanceledOnTouchOutside(false);
        serviceDialog.show();
        serviceDialog.init();
        serviceDialog.updateWifiName(ssid);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showDialog("ddd");
        return START_STICKY;
    }
}
