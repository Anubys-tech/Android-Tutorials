package com.anubys.example.firebaseinsertobjectdemo;

/** @Autor Created by Anubys on the 06.04.2020 */


public class ModellEvent {
    private String name;
    private String place;
    private String description;
    private String date;
    private String key;

    public ModellEvent() {
    }

    public ModellEvent(String name, String place, String description, String date, String key) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.date = date;
        this.key = key;
    }

    public String getName() {
        return (name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrt() {
        return (place);
    }

    public void setOrt(String ort) {
        this.place = ort;
    }

    public String getBeschreibung() {
        return (description);
    }

    public void setBeschreibung(String beschreibung) {
        this.description = beschreibung;
    }

    public String getDatum() {
        return (date);
    }

    public void setDatum(String datum) {
        this.date = datum;
    }

    public String getKey() {
        return (key);
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public String toString() {
        return getName();
    }
}
