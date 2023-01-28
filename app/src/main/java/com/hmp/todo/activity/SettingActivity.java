package com.hmp.todo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.slider.Slider;
import com.hmp.todo.R;

public class SettingActivity extends AppCompatActivity {
    private Switch nightModeSwitch;

    private Slider slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //For night mode
        nightModeSwitch = findViewById(R.id.nihtModeSwitch);
        setActionToSwitch();
        // changing text size
        slider = findViewById(R.id.txtSizeSlider);
        setTextSize();

    }

    private void setActionToSwitch() {
        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            Log.d("DEBUG", "onCheckedChanged: " + isChecked);
            if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(this, "Night mode is Off", Toast.LENGTH_LONG).show();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(this, "Night mode is On", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTextSize() {
        Slider slider = findViewById(R.id.txtSizeSlider);
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            float spinnerValue = 0;
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
               spinnerValue =  slider.getValue();

            }
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                Toast.makeText(SettingActivity.this, "Font size set to: "+spinnerValue, Toast.LENGTH_LONG).show();

            }
        });

    }


}