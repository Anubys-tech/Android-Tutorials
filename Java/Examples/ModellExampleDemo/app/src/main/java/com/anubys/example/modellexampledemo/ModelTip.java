package com.anubys.example.modellexampledemo;

import androidx.annotation.NonNull;

/** @Autor Created by Anubys on the 29.03.2020 */


public class ModelTip {
    private static final String TAG = ModelTip.class.getSimpleName();

    //* ************************************************ *
    //*               A T T R I B U T E S                *
    //* ************************************************ *
    private String description;
    private String target;
    private String user;
    private String info;


    //* ************************************************ *
    //*              K O N S T R U K T O R               *
    //* ************************************************ *
    public ModelTip(String description, String target, String user, String info) {
        this.description = description;
        this.target = target;
        this.user = user;
        this.info = info;
    }

    //* ************************************************ *
    //*          G E T T E R - M E T H O D E N           *
    //* ************************************************ *
    public String getDescription() {
        return (description);
    }

    public String getTarget() {
        return (target);
    }

    public String getUser() {
        return (user);
    }

    public String getInfo() {
        return (info);
    }


    //* ************************************************ *
    //*          S E T T E R - M E T H O D E N           *
    //* ************************************************ *
    public void setDescription(String description) {
        this.description = description;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @NonNull
    @Override
    public String toString() {
        return (getDescription() + " von: " + getUser());
    }
}
