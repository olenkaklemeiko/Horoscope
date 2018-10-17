package com.example.olenka.horoscope.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsManager {

    public static final Date TIME = new Date(0, 0, 0, 10, 45);

    private static SettingsManager settingsManager;

    private SharedPreferencesUtils sharedPreferencesUtils;


    private SettingsManager(SharedPreferencesUtils utils) {
        sharedPreferencesUtils = utils;
    }

    public static void initSettingsManager(SharedPreferencesUtils utils) {
        settingsManager = new SettingsManager(utils);
    }

    public static SettingsManager getSettingManager() {
        if (settingsManager == null) {
            throw new NullPointerException("Setting manager is not initialized.");
        }
        return settingsManager;
    }

    public boolean getSwitch() {
        return sharedPreferencesUtils.loadSwitch();
    }

    public void setSwitch(boolean switchS) {
        sharedPreferencesUtils.saveSwitch(switchS);
    }

    public Date getTime() {
        try {
            return new SimpleDateFormat("HH:mm").parse(sharedPreferencesUtils.loadTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return TIME;
        }
    }

    public void setTime(Date time){
        sharedPreferencesUtils.saveTime(time);
    }
}
