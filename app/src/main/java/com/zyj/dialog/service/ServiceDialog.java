package com.zyj.dialog.service;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyj.dialog.R;

/**
 * Created by yh on 8/15/17.
 */

public class ServiceDialog extends Dialog {

    private TextView wifi_ssid_tv;
    private TextView wifi_detect_tv;
    private OnEventListener onEventListener;
    private ImageView close_image;

    public ServiceDialog(Context context) {
        super(context, R.style.yibaDialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_dialog);

        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        Window localWindow = this.getWindow();
        localWindow.getAttributes();
        //重新设置
        WindowManager.LayoutParams lp = localWindow.getAttributes();
//        lp.y = -300; // 新位置Y坐标

        // dialog.onWindowAttributesChanged(lp);
        //(当Window的Attributes改变时系统会调用此函数)
        localWindow.setAttributes(lp);

        wifi_ssid_tv = (TextView) findViewById(R.id.wifi_ssid_tv);
        wifi_detect_tv = (TextView) findViewById(R.id.wifi_detect_tv);
        close_image = (ImageView) findViewById(R.id.close_image);

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    //拦截系统返回键
                }
                return false;
            }
        });
    }

    public void init() {
        wifi_detect_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEventListener != null) {
                    onEventListener.onSure();
                }
            }
        });

        close_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEventListener != null) {
                    onEventListener.onCancel();
                }
            }
        });
    }

    public void updateWifiName(String wifiName) {
        wifi_ssid_tv.setText(wifiName);
    }

    public void setOnEventListener(OnEventListener onEventListener) {
        this.onEventListener = onEventListener;
    }

    public interface OnEventListener {
        void onSure();

        void onCancel();
    }
}
