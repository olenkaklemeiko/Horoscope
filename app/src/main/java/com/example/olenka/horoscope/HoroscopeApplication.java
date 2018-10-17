package com.example.olenka.horoscope;

import android.app.Application;
import android.content.Context;

import com.example.olenka.horoscope.utils.SettingsManager;
import com.example.olenka.horoscope.utils.SharedPreferencesUtils;

public class HoroscopeApplication extends Application{

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SettingsManager.initSettingsManager(SharedPreferencesUtils.getSPUtils(this));
  }
}
