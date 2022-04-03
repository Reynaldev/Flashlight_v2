package com.reyncrs.flashlight;

import android.app.AlertDialog;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CameraManager mCameraManager;
    public String mCameraId;

    public ToggleButton power;
    public ImageButton info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        power = (ToggleButton) findViewById(R.id.btn_toggle);
        info = (ImageButton) findViewById(R.id.btn_info);

        power.setOnClickListener(this);
        info.setOnClickListener(this);

        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (power.equals(view)) {
            if (power.isChecked()) {
                try {
                    mCameraManager.setTorchMode(mCameraId,true);

                    Toast.makeText(this, "Flashlight turned ON", Toast.LENGTH_SHORT).show();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    mCameraManager.setTorchMode(mCameraId,false);

                    Toast.makeText(this, "Flashlight turned OFF", Toast.LENGTH_SHORT).show();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        } else if (info.equals(view)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_info)
                    .setNegativeButton(R.string.ok, (dialog, id) -> {
                        // User cancelled the dialog
                    });
            builder.show();
        }
    }
}