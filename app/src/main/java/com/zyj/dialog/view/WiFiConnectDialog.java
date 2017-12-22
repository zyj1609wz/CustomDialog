package com.zyj.dialog.view;

import android.content.Context;
import android.content.DialogInterface;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyj.dialog.R;

/**
 * Created by ${zyj} on 2016/10/11.
 */

public class WiFiConnectDialog extends BaseDialog  {


    public WiFiConnectDialog(Context context) {
        super(context);
    }

    public WiFiConnectDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onTouchOutside() {

    }

    public static class Builder {
        private Context context;
        private String ssid ;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private boolean showPass = true ;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setSsid(String ssid ) {
            this.ssid = ssid ;
            return this;
        }

        public Builder setPositiveButton(OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }

        public WiFiConnectDialog create( ) {
            showPass = true ;

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final WiFiConnectDialog dialog = new WiFiConnectDialog(context,  R.style.DialogStyle );
            View layout = inflater.inflate( R.layout.wifi_connect_dialog , null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView wifi_ssid_tv = (TextView) layout.findViewById( R.id.wifi_ssid_tv);
            wifi_ssid_tv.setText( ssid );

            final EditText editText = (EditText) layout.findViewById( R.id.wifi_password_et );

            LinearLayout show_password_lin = (LinearLayout) layout.findViewById( R.id.show_password_lin );

            final ImageView show_pass_image = (ImageView) layout.findViewById( R.id.show_pass_image);

            TextView wifi_cancle = (TextView) layout.findViewById( R.id.wifi_cancle);
            TextView wifi_ok = (TextView) layout.findViewById( R.id.wifi_ok);

            show_password_lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( showPass ){
                        show_pass_image.setImageResource( R.mipmap.show_wifi_password_no );
                        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showPass = false ;
                    }else {
                        show_pass_image.setImageResource( R.mipmap.show_wifi_password );
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showPass = true ;
                    }
                }
            });

            wifi_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (negativeButtonClickListener != null) {
                        negativeButtonClickListener.onClick(dialog,
                                DialogInterface.BUTTON_NEGATIVE);
                    }
                }
            });

            wifi_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( positiveButtonClickListener != null ){
                        positiveButtonClickListener.onClick( dialog , DialogInterface.BUTTON_POSITIVE );
                    }
                }
            });

            dialog.setContentView(layout);
            return dialog;
        }
    }
}
