package com.example.olenka.horoscope.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

  public void replaceFragment(Fragment fragment, int container) {
    replaceFragment(fragment, container, false);
  }

  public void replaceFragment(Fragment fragment, int container, boolean addToBackStack) {
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(container, fragment, fragment.getTag());
    if (addToBackStack) {
      transaction.addToBackStack(fragment.getClass().getName());
    }
    transaction.commit();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
      fragment.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override
  public void onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
      getSupportFragmentManager().popBackStack();
    } else {
      super.onBackPressed();
      finish();
    }
  }
}
