package com.example.ems;

public class Transection {
    private String id;
    private String Name;
    private String SureName;
    private String Event;
    private String CNIC;
    private String Age;



    public Transection(String id, String name, String sureName, String event, String CNIC, String age) {
        this.id = id;
        Name = name;
        SureName = sureName;
        Event = event;
        this.CNIC = CNIC;
        Age = age;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSureName() {
        return SureName;
    }

    public void setSureName(String sureName) {
        SureName = sureName;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

}
