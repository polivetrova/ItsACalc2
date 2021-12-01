package com.example.itsacalc2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.itsacalc2.R;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends MainActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_settings);

        findViewById(R.id.back_arrow).setOnClickListener(v -> finish());

        initThemeChooser();
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.blue_orangeade_theme_button), themeBlueOrangeade);
        initRadioButton(findViewById(R.id.blue_and_gray_theme_button), themeBlueAndGray);
        RadioGroup rg = findViewById(R.id.radio_group);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(themeBlueOrangeade))).setChecked(true);
    }

    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(v -> {
            setAppTheme(codeStyle);
            recreate();
        });
    }
}
