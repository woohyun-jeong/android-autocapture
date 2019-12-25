package com.whjung.android_autocapture.facetracker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.whjung.android_autocapture.R;

import java.io.IOException;
import java.io.InputStream;

public class LicenseDialog extends Dialog
{
    LicenseDialog m_oDialog;
    public LicenseDialog(Context context)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_license);

        m_oDialog = this;
        TextView tvLicense = (TextView) this.findViewById(R.id.tv_license);

        String assetTxt = "";
        try {
            assetTxt = readText("LICENSE.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvLicense.setText(assetTxt);

        Button btnBtn = (Button)this.findViewById(R.id.btn_license);
        btnBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
    }

    private String readText(String file) throws IOException {
        InputStream is = getContext().getAssets().open(file);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        String text = new String(buffer);

        return text;
    }


}
