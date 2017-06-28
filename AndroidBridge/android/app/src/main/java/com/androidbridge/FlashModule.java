package com.androidbridge;

import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Map;
import java.util.HashMap;

public class FlashModule extends ReactContextBaseJavaModule {

  private Camera camera;
  private boolean isFlashOn;
  private boolean hasFlash;
  private Parameters params;
  private MediaPlayer mp;

  public FlashModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "FlashModule";
  }
  
  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();

    return constants;
  }

  @ReactMethod
    public void show() {
        
      hasFlash = getReactApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
      if (!hasFlash) {
        // device doesn't support flash
        // Show alert message and close the application
      }

      // turnOnFlash();
  }

  // getting camera parameters
  private void getCamera() {
    if (camera == null) {
      try {
        camera = Camera.open();
        params = camera.getParameters();
      } catch (RuntimeException e) {
        // Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
      }
    }
  }

  /*
  * Toggling the Flash light
  */
  @ReactMethod
    public void turnOnFlash() {
    if (!isFlashOn) {
      camera = Camera.open();
      params = camera.getParameters();
      params.setFlashMode(Parameters.FLASH_MODE_TORCH);
      camera.setParameters(params);
      camera.startPreview();
      isFlashOn = true;
    }else{
      params = camera.getParameters();
      params.setFlashMode(Parameters.FLASH_MODE_OFF);
      camera.setParameters(params);
      camera.stopPreview();
      isFlashOn = false;
    }
  }

}
