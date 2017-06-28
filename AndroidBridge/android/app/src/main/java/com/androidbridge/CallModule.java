package com.androidbridge;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import android.content.Context;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Map;
import java.util.HashMap;

public class CallModule extends ReactContextBaseJavaModule {

  private Context context;

  public CallModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.context = reactContext;
  }

  @Override
  public String getName() {
    return "CallModule";
  }

  /* ---------------------------------------------------
	 * Call Methods and State listeners
	 */
 @ReactMethod
	public void call(String phonenum)
  {
		  // PhoneCallListener phoneListener = new PhoneCallListener(this.context);
		  // TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService(Context.TELEPHONY_SERVICE);
		  // telephonyManager.listen(phoneListener,
		  //         PhoneStateListener.LISTEN_CALL_STATE);

		  Intent callIntent = new Intent(Intent.ACTION_CALL);
      callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		  callIntent.setData(Uri.parse("tel:" + phonenum));
		  this.context.startActivity(callIntent);

  }

	private class PhoneCallListener extends PhoneStateListener {

      private boolean isPhoneCalling = false;
      private Context context;

      String LOG_TAG = "LOGGING 123";

      public PhoneCallListener(Context reactContext) {
        // super(reactContext);
        this.context = reactContext;
      }

      @Override
      public void onCallStateChanged(int state, String incomingNumber) {

          if (TelephonyManager.CALL_STATE_RINGING == state) {
              // phone ringing
              Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
          }

          if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
              // active
              Log.i(LOG_TAG, "OFFHOOK");

              isPhoneCalling = true;
          }

          if (TelephonyManager.CALL_STATE_IDLE == state) {
              // run when class initial and phone call ended, need detect flag
              // from CALL_STATE_OFFHOOK
              Log.i(LOG_TAG, "IDLE");

              if (isPhoneCalling) {

                  Log.i(LOG_TAG, "restart app");

                  // restart app
                  Intent i = this.context.getPackageManager()
                          .getLaunchIntentForPackage(
                                  this.context.getPackageName());
                  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  this.context.startActivity(i);

                  isPhoneCalling = false;
              }

          }
      }
  }

}
