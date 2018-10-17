package com.example.olenka.horoscope;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.olenka.horoscope.base.BaseActivity;
import com.example.olenka.horoscope.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZodiacsFragment extends BaseFragment {

 // InformationFragment informationFragment;
  Fragment informationFragment;
  ImageView imageView;

  @Override
  protected int layoutResId() {
    return R.layout.fragment_zodiacs;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    informationFragment = new InformationFragment();
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ButterKnife.bind(this, view);
  }

 /* @OnClick({R.id.aries, R.id.taurus, R.id.gemini, R.id.cancer, R.id.leo, R.id.virgo, R.id.libra, R.id.scorpio,
               R.id.sagittarius, R.id.capricorn, R.id.aquarius, R.id.pisces})
  public void replaceFragmentL() {
    ((MainActivity) getActivity()).replaceFragment(new InformationFragment());
  }*/

  @OnClick(R.id.settings)
  public void onClick() {
    SettingsFragment settingsFragment = new SettingsFragment();
    settingsFragment.show(getFragmentManager(), "Dialog");
  }




  /*@OnClick({R.id.aries, R.id.taurus, R.id.gemini, R.id.cancer, R.id.leo, R.id.virgo, R.id.libra, R.id.scorpio,
               R.id.sagittarius, R.id.capricorn, R.id.aquarius, R.id.pisces})
  public void onClickIcons(View view){

    switch (view.getId()){
      case R.id.aries:
        informationFragment.setImage(R.drawable.ic_aries);

    }

  }*/

  @OnClick(R.id.aries)
  public void onClickAries(View view){


    ((ImageButton)view).

    InformationFragment fragment = InformationFragment.getInstance(R.drawable.ic_aries);
    ((BaseActivity)getActivity()).replaceFragment(fragment, R.id.container, true);

  }

}
