package com.example.olenka.horoscope.notification;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.olenka.horoscope.MainActivity;
import com.example.olenka.horoscope.R;
import com.example.olenka.horoscope.utils.SettingsManager;

public class HoroscopeNotificationIntentService extends IntentService {

  public NotificationManager manager;
  private final int NOTIFICATION_ID = 127;
  public static final String NOTIFICATION_CHANNEL_ID = "_Horoscope_";

  SettingsManager settingsManager = SettingsManager.getSettingManager();

  public HoroscopeNotificationIntentService() {
    super("HoroscopeNotificationIntentService");
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {

    manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

    intent = new Intent(getApplicationContext(), MainActivity.class);
    intent.putExtra("menuFragment", "favoritesMenuItem");

    PendingIntent pendingIntent =
        PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    builder
        .setContentIntent(pendingIntent)
        .setSmallIcon(R.drawable.ic_sun)
        .setColor(Color.argb(255, 0, 25, 225))
        .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.ic_libra))
        .setTicker("Нове повідомлення")
        .setWhen(System.currentTimeMillis())
        .setShowWhen(true)
        .setAutoCancel(true)
        .setContentTitle("Повідомлення")
        .setContentText("Натисніть щоб ...");

  /*  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
        1000 * 60 * 60 * 24, pendingIntent);*/

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      int importance = NotificationManager.IMPORTANCE_HIGH;
      NotificationChannel
          notificationChannel =
          new NotificationChannel(NOTIFICATION_CHANNEL_ID, "daily_horoscope", importance);
      notificationChannel.enableLights(true);
      notificationChannel.setLightColor(Color.MAGENTA);
      notificationChannel.enableVibration(true);
      notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
      assert manager != null;
      builder.setChannelId(NOTIFICATION_CHANNEL_ID);
      manager.createNotificationChannel(notificationChannel);
    }
    manager.notify(NOTIFICATION_ID, builder.build());

    notificationsTime();
  }

  /*   public void notificationsTime() {
         Intent intent = new Intent(getActivity(), HoroscopeNotificationReceiver.class);
         PendingIntent pendingIntent = PendingIntent.getBroadcast
                 (getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
         AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
         alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                 settingsManager.getTime().getHours() + settingsManager.getTime().getMinutes(), pendingIntent);
     }*/
  public void notificationsTime() {

    Intent intent = new Intent(this, HoroscopeNotificationReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast
        (this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
        settingsManager.getTime().getHours() + settingsManager.getTime().getMinutes(), 0, pendingIntent);
  }
}

