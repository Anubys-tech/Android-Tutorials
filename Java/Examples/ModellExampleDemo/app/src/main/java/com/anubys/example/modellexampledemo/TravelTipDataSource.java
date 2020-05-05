package com.anubys.example.modellexampledemo;

import android.util.Log;

import java.util.ArrayList;

/** @Autor Created by Anubys on the 29.03.2020 */


public class TravelTipDataSource {
    private static final String TAG = TravelTipDataSource.class.getSimpleName();


    //* ************************************************ *
    //*               A T T R I B U T E S                *
    //* ************************************************ *
    private ArrayList<ModelTip> arrayListData = new ArrayList<>();


    //* ************************************************ *
    //*              K O N S T R U K T O R               *
    //* ************************************************ *
    public TravelTipDataSource() {
        arrayListData.add(new ModelTip("Erleben die Stadt", "London", "Frank", "Die Londoner Uban ist toll"));
        arrayListData.add(new ModelTip("New York big Tower", "USA", "Jan", "Erlebe was neues"));
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    public ArrayList<ModelTip> getData() {
        Log.d(TAG, "TAG - TravelTipDataSource - getData()");
        return (arrayListData);
    }
}
