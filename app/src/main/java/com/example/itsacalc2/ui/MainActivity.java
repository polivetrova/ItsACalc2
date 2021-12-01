package com.example.itsacalc2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.itsacalc2.R;
import com.example.itsacalc2.domain.CalcImpl;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalcView {

    MaterialButton percentButton, multiplyButton, minusButton, plusButton, divideButton, pointButton, deleteButton, equalsButton;
    MaterialTextView editText;

    private static final String appTheme = "APP_THEME";
    private static final String nameSharedPreference = "THEME";
    protected static final int themeBlueOrangeade = 0;
    protected static final int themeBlueAndGray = 1;

    protected CalcPresenter calcPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getAppTheme(R.style.Theme_Blue_Orangeade));

        setContentView(R.layout.activity_main);

        findViewById(R.id.settings_button).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        });

        calcPresenter = new CalcPresenter(new CalcImpl(), this);

        editText = findViewById(R.id.edit_text);

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_0, 0);
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);

        percentButton = findViewById(R.id.key_percent);
        pointButton = findViewById(R.id.key_point);
        divideButton = findViewById(R.id.key_divide);
        multiplyButton = findViewById(R.id.key_multipl);
        plusButton = findViewById(R.id.key_plus);
        minusButton = findViewById(R.id.key_minus);
        deleteButton = findViewById(R.id.key_delete);
        equalsButton = findViewById(R.id.key_equals);

        View.OnClickListener digitsClickListener = v -> calcPresenter.onDigitPressed(getEditTextContents(), digits.get(v.getId()));

        findViewById(R.id.key_0).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitsClickListener);

        pointButton.setOnClickListener(v -> calcPresenter.onPointPressed(getEditTextContents(), pointButton));

        deleteButton.setOnClickListener(v -> calcPresenter.onDeletePressed());

        plusButton.setOnClickListener(v -> calcPresenter.onPlusPressed(getEditTextContents()));

        minusButton.setOnClickListener(v -> calcPresenter.onMinusPressed(getEditTextContents(), minusButton));

        multiplyButton.setOnClickListener(v -> calcPresenter.onMultiplyPressed(getEditTextContents()));

        divideButton.setOnClickListener(v -> calcPresenter.onDividePressed(getEditTextContents()));

        percentButton.setOnClickListener(v -> calcPresenter.onPercentPressed(getEditTextContents()));

        equalsButton.setOnClickListener(v -> calcPresenter.onEqualsPressed(getEditTextContents()));
    }

    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(nameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
        recreate();
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case themeBlueOrangeade:
                return R.style.Theme_Blue_Orangeade;
            case themeBlueAndGray:
                return R.style.Blue_and_Gray;
        }
        return R.style.Theme_Blue_Orangeade;
    }

    protected int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(nameSharedPreference, MODE_PRIVATE);
        return sharedPreferences.getInt(appTheme, codeStyle);
    }

    @NonNull
    private String getEditTextContents() {
        return editText.getText().toString();
    }

    @Override
    public void showResult(String result) {
        editText.setText(result);
    }

}