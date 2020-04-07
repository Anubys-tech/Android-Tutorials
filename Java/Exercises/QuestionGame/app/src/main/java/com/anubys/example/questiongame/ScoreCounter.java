package com.anubys.example.questiongame;

import android.util.Log;

/** @Autor Created by Anubys on the 28.03.2020 */


class ScoreCounter {
    private static final String TAG = MainActivity.class.getSimpleName();

    //*************************************************
    //*               A T T R I B U T E               *
    //*************************************************
    private int points;


    //* *********************************************** *
    //*           K O N S T R U K T O R E N             *
    //* *********************************************** *
    ScoreCounter() { }


    //* ************************************************ *
    //*           G E T T E R - M E T H O D E N          *
    //* ************************************************ *
    int getPoints() {
        Log.d(TAG, "TAG - ScoreCounter - getPoints()");
        return (points);
    }


    //* *********************************************** *
    //*          S E T T E R - M E T H O D E N          *
    //* *********************************************** *
    void setPoints() {
        Log.d(TAG, "TAG - ScoreCounter - setPoints()");
        this.points = 0;
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    void right() {
        Log.d(TAG, "TAG - ScoreCounter - right()");
        int punkteFuerRichtigeLoesung = 10;
        points += punkteFuerRichtigeLoesung;
    }

    void wrong() {
        Log.d(TAG, "TAG - ScoreCounter - wrong()");
        int punkteabzugbeiFalscherLoesung = 5;
        points -= punkteabzugbeiFalscherLoesung;
    }
}
