package com.anubys.example.sqlitedemo;

import androidx.annotation.NonNull;

/** @Autor Created by Anubys on the 30.03.2020 */


public class ModelText {
    private static final String TAG = ModelText.class.getSimpleName();

    //* ************************************************ *
    //*               A T T R I B U T E S                *
    //* ************************************************ *
    private int id;
    private String text;


    //* ************************************************ *
    //*            K O N S T R U K T O R E N             *
    //* ************************************************ *
    public ModelText() { }

    public ModelText(String text) {
        this.id = id;
        this.text = text;
    }


    //* ************************************************ *
    //*          G E T T E R - M E T H O D E N           *
    //* ************************************************ *
    public int getId() {
        return (id);
    }

    public String getText() {
        return (text);
    }


    //* ************************************************ *
    //*          S E T T E R - M E T H O D E N           *
    //* ************************************************ *
    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return ("ID: " + getId() + "," + "Text: " + getText());
    }
}
