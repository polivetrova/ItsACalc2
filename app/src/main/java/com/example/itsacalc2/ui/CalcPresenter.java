package com.example.itsacalc2.ui;

import android.widget.Button;

import com.example.itsacalc2.domain.CalcModel;
import com.example.itsacalc2.domain.Operations;
import com.google.android.material.button.MaterialButton;

public class CalcPresenter {
    private final CalcModel calcModel;
    private final CalcView calcView;

    private float valueOne, valueTwo;
    private String result = null;
    private boolean doAddition, doSubtraction, doMultiplication, doDivision, doPercentage;


    public CalcPresenter(CalcModel calcModel, CalcView calcView) {
        this.calcModel = calcModel;
        this.calcView = calcView;
    }

    public void onDigitPressed(String editTextContent, Integer integer) {
        calcView.showResult(editTextContent + integer);
    }

    public void onPointPressed(String editTextContent, MaterialButton pointButton) {
        calcView.showResult(editTextContent + pointButton.getText().toString());
    }

    public void onDeletePressed() {
        calcView.showResult(null);
        valueOne = 0;
        valueTwo = 0;
    }

    public void onPlusPressed(String editTextContent) {
        if (doDivision || doPercentage || doMultiplication || doSubtraction) {
            calcView.showResult(editTextContent);
        }
        saveFirstValue(editTextContent);
        doAddition = true;
    }

    public void onMinusPressed(String editTextContent, MaterialButton minus) {
        saveFirstValue(editTextContent);
        if (valueOne == 0.0f) {
            calcView.showResult(minus.getText().toString());
        } else if (doPercentage || doMultiplication || doAddition || doDivision) {
            calcView.showResult(editTextContent);
        } else {
            doSubtraction = true;
        }
    }

    public void onMultiplyPressed(String editTextContent) {
        if (doDivision || doPercentage || doAddition || doSubtraction) {
            calcView.showResult(editTextContent);
        }
        saveFirstValue(editTextContent);
        doMultiplication = true;
    }

    public void onDividePressed(String editTextContent) {
        if (doAddition || doPercentage || doMultiplication || doSubtraction) {
            calcView.showResult(editTextContent);
        }
        saveFirstValue(editTextContent);
        doDivision = true;
    }

    public void onPercentPressed(String editTextContent) {
        if (doDivision || doAddition || doMultiplication || doSubtraction) {
            calcView.showResult(editTextContent);
        }
        saveFirstValue(editTextContent);
        doPercentage = true;
    }

    public void onEqualsPressed(String editTextContent) {

        if (valueOne == 0.0f && valueTwo == 0.0f && result == null) {
            calcView.showResult(null);
        } else if (doPercentage) {
            result = calcModel.doPercentage(valueOne);
            calcView.showResult(result);
            doPercentage = false;
        } else {
            valueTwo = Float.parseFloat(editTextContent);

            if (doAddition) {
                result = calcModel.doOperation(Operations.ADD, valueOne, valueTwo);
                calcView.showResult(result);
                doAddition = false;
            }

            if (doSubtraction) {
                result = calcModel.doOperation(Operations.SUB, valueOne, valueTwo);
                calcView.showResult(result);
                doSubtraction = false;
            }

            if (doMultiplication) {
                result = calcModel.doOperation(Operations.MULT, valueOne, valueTwo);
                calcView.showResult(result);
                doMultiplication = false;
            }

            if (doDivision) {
                result = calcModel.doOperation(Operations.DIV, valueOne, valueTwo);
                calcView.showResult(result);
                doDivision = false;
            }
        }
    }

    private void saveFirstValue(String editTextContent) {
        if (!editTextContent.isEmpty()) {
            valueOne = Float.parseFloat(editTextContent);
        }
        calcView.showResult(null);
    }
}
