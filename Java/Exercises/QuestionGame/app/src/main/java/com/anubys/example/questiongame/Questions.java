package com.anubys.example.questiongame;

import android.util.Log;

/** @Autor Created by Anubys on the 28.03.2020 */


class Questions {
    private static final String TAG = MainActivity.class.getSimpleName();

    //*************************************************
    //*               A T T R I B U T E               *
    //*************************************************
    private String[] taskArray = {"Angela Merkel ist eine Frau?", "VW ist ein Flugzeug?", "Die Fragen sind voll unnötig?"};


    //* *********************************************** *
    //*           K O N S T R U K T O R E N             *
    //* *********************************************** *
    Questions() { }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    String showTask() {
        Log.d(TAG, "TAG - Questens - showTask()");
        int random = (int) (Math.random() * taskArray.length);
        Log.i(TAG, "Generierte Zahl " + random);

        return (taskArray[random]);
    }
}
