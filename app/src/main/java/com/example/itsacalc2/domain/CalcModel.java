package com.example.itsacalc2.domain;

public interface CalcModel {
    String doOperation(Operations operation, float valueOne, float valueTwo);
    String doPercentage(float valueOne);
}
