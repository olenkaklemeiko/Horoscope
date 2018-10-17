package com.example.olenka.horoscope;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import com.example.olenka.horoscope.base.BaseActivity;
import com.example.olenka.horoscope.utils.SharedPreferencesUtils;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SharedPreferencesUtils.getSPUtils(this);

    String fragment = getIntent().getStringExtra("menuFragment");

    if (fragment != null) {
      if (fragment.equals("favoritesMenuItem")) {
        replaceFragment(new InformationFragment());
      }
    } else {
        replaceFragment(new MainFragment());

      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          replaceFragment(new  ZodiacsFragment());
        }
      }, 3000);
    }

    /*if (savedInstanceState == null) {
      replaceFragment(new MainFragment());
    }*/

  }

  public void replaceFragment(Fragment fragment) {
    super.replaceFragment(fragment, R.id.container, true);
  }

}
