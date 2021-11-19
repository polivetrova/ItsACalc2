package com.example.itsacalc2.domain;

import java.util.Locale;

public class CalcImpl implements CalcModel {

    @Override
    public String doOperation(Operations operation, float valueOne, float valueTwo) {

        switch (operation) {

            case ADD:
                if ((valueOne + valueTwo) % 1 == 0) {
                    return String.format(Locale.ROOT, "%.0f", (valueOne + valueTwo));
                } else {
                    return Float.toString(valueOne + valueTwo);
                }

            case SUB:
                if ((valueOne - valueTwo) % 1 == 0) {
                    return String.format(Locale.ROOT, "%.0f", (valueOne - valueTwo));
                } else {
                    return Float.toString(valueOne - valueTwo);
                }

            case MULT:
                if ((valueOne * valueTwo) % 1 == 0) {
                    return String.format(Locale.ROOT, "%.0f", (valueOne * valueTwo));
                } else {
                    return Float.toString(valueOne * valueTwo);
                }

            case DIV:
                if ((valueOne / valueTwo) % 1 == 0) {
                    return String.format(Locale.ROOT, "%.0f", (valueOne / valueTwo));
                } else {
                    return Float.toString(valueOne / valueTwo);
                }
        }
        return "";
    }

    @Override
    public String doPercentage(float valueOne) {
        return Float.toString(valueOne / 100);
    }
}