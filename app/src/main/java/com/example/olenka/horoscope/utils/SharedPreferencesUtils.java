package com.example.olenka.horoscope.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StyleRes;

import java.util.Calendar;
import java.util.Date;

public class SharedPreferencesUtils {

  private static final String DIALOG_SETTINGS = "settings_dialog";
  private static final String SWITCH = "";
  private static final String TIME_KEY = "time_key";

  private static SharedPreferencesUtils utils;

  private SharedPreferences preferences;


  private SharedPreferencesUtils(Context context){
    preferences = context.getSharedPreferences(DIALOG_SETTINGS, Context.MODE_PRIVATE);
  }

  public static SharedPreferencesUtils getSPUtils(Context context) {
    if (null == utils) {
      utils = new SharedPreferencesUtils(context);
    }
    return utils;
  }

  public void saveSwitch(boolean switchB){
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean(SWITCH, switchB);
    editor.apply();
  }

  public boolean loadSwitch(){
    return preferences.getBoolean(SWITCH, false);
  }

  public void saveTime(Date timeChange){
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(TIME_KEY, timeChange.getHours() + ":" + timeChange.getMinutes());
    editor.apply();
  }

  public String loadTime(){
    return preferences.getString(TIME_KEY, SettingsManager.TIME.toString());

  }
}
