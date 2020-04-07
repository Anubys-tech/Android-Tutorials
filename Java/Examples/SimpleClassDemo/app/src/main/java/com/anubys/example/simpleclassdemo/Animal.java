package com.anubys.example.simpleclassdemo;

/** @Autor Created by Anubys on the 27.03.2020 */


public class Animal {
    //*************************************************
    //*               A T T R I B U T E               *
    //*************************************************
    private String sName;
    private int iAge;
    private double dSize;


    //* *********************************************** *
    //*           K O N S T R U K T O R E N             *
    //* *********************************************** *
    public Animal() {}


    //*************************************************
    //*         G E T T E R - M E T H O D E N         *
    //*************************************************
    public String getsName() {
        return (sName);
    }

    public int getiAge() {
        return (iAge);
    }

    public double getdSize() {
        return (dSize);
    }


    //*************************************************
    //*         S E T T E R - M E T H O D E N         *
    //*************************************************
    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setiAge(int iAge) {
        this.iAge = iAge;
    }

    public void setdSize(double dSize) {
        this.dSize = dSize;
    }
}
