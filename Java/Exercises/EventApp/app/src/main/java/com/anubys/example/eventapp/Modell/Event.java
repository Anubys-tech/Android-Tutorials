package com.anubys.example.eventapp.Modell;

/** @Author Created by Anubys on the 07.06.2020 */


public class Event {
    private static final String TAG = Event.class.getSimpleName();

    private String name;
    private String place;
    private String description;
    private String date;
    private String key;

    public Event() {
    }

    public Event(String name, String place, String description, String date, String key) {
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

    public String getPlace() {
        return (place);
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return (description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return (date);
    }

    public void setDate(String date) {
        this.date = date;
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
