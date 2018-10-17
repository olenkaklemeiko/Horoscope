package com.example.olenka.horoscope;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.olenka.horoscope.base.BaseFragment;

public class InformationFragment extends BaseFragment {

  public static final String ICON_RES_KEY = "icon_key";

  ImageView setNewImage;

  public static InformationFragment getInstance(@DrawableRes int iconRes){
    InformationFragment fragment = new InformationFragment();

    Bundle bundle = new Bundle();
    bundle.putInt(ICON_RES_KEY, iconRes);
    fragment.setArguments(bundle);

    return fragment;
  }


  @Override
  protected int layoutResId() {
    return R.layout.fragment_information;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setNewImage = view.findViewById(R.id.set_image);
    Bundle bundle = getArguments();
    if (bundle != null) {
      setNewImage.setImageResource(bundle.getInt(ICON_RES_KEY));
    }
  }
}
