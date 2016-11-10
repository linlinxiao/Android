package com.linlin.torchdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isOpen = false;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.toggleBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!checkPermissionGranted(Manifest.permission.CAMERA)){
            Toast.makeText(this, "摄像头未授权", Toast.LENGTH_SHORT).show();
            verifyStoragePermissions();
            return;
        }
        if (!isOpen) {
            Toast.makeText(this, "手电筒已经打开", Toast.LENGTH_SHORT).show();
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();

            isOpen = true;
        } else {
            Toast.makeText(this, "手电筒已经关闭", Toast.LENGTH_SHORT).show();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();

            isOpen = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    private final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA
    };

    public void verifyStoragePermissions() {
        // We don't have permission so prompt the user
        ActivityCompat.requestPermissions(
                this,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
        );
    }

    public boolean checkPermissionGranted(String permission) {
        // Check if we have write permission
        int p = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (p == PackageManager.PERMISSION_GRANTED) return true;
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
        }
    }
}
