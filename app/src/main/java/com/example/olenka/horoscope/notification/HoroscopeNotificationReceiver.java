package com.example.olenka.horoscope.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HoroscopeNotificationReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {

    Intent intent1 = new Intent(context, HoroscopeNotificationIntentService.class);
    context.startService(intent1);
  }
}
