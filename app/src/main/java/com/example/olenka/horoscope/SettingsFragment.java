package com.example.olenka.horoscope;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.olenka.horoscope.utils.SettingsManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends DialogFragment {

    @BindView(R.id.time_change)
    TextView timeChange;
    @BindView(R.id.time)
    TextView textTime;
    @BindView(R.id.switch_)
    Switch aSwitch;

    SettingsManager settingsManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        timeChange.setText(settingsManager.getTime().getHours() + ":" + settingsManager.getTime().getMinutes());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        settingsManager = SettingsManager.getSettingManager();

        View view = inflater.inflate(R.layout.fragment_settings, null);

        ButterKnife.bind(this, view);

        aSwitch.setChecked(settingsManager.getSwitch());
        timeChange.setText(settingsManager.getTime().getHours() + ":" + settingsManager.getTime().getMinutes());

        onClickSwitch();

        builder.setView(view);
        builder.setTitle("Settings");

        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    settingsManager.setTime(new SimpleDateFormat("HH:mm").parse(timeChange.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.time_setting_layout)
    public void onClickOk() {
        getTimePicker();
    }

    @OnClick(R.id.switch_)
    public void onClickSwitch() {
        settingsManager.setSwitch(aSwitch.isChecked());
        if (settingsManager.getSwitch()) {
            textTime.setEnabled(true);
            timeChange.setEnabled(true);
            //notificationsTime();
        } else {
            textTime.setEnabled(false);
            timeChange.setEnabled(false);
        }
    }

    private void getTimePicker() {
        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeSet = hourOfDay + ":" + minute;
                timeChange.setText(timeSet);
            }
        }, settingsManager.getTime().getHours(), settingsManager.getTime().getMinutes(), false);
        timePicker.show();
    }


}
