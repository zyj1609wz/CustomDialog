package com.zyj.dialog.view;

import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zyj.dialog.R;

/**
 * Created by ${zyj} on 2016/10/11.
 */

public class Dialog1 extends BaseDialog {


    public Dialog1(Context context) {
        super(context);
    }

    public Dialog1(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onTouchOutside() {

    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }


        public Dialog1 create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final Dialog1 dialog = new Dialog1(context, R.style.dialog1);
            View layout = inflater.inflate(R.layout.dialog1, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            //获取屏幕的宽度
            Window dialogWindow = dialog.getWindow();
            WindowManager windowManager = dialogWindow.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int phoneWidth = display.getWidth();

            //自定义 Dialog 宽度
            WindowManager.LayoutParams attr = dialogWindow.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = (int) (phoneWidth * 0.9);
                attr.gravity = Gravity.CENTER;//设置dialog 在布局中的位置
            }

            dialogWindow.setAttributes(attr);

            dialog.setContentView(layout);
            return dialog;
        }
    }
}
