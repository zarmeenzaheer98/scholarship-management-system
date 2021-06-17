package com.example.ems;

public class Scholarship {
    private String Title;
    private String organizer;
    private String Description;
    private String cgpa;
    private String Province;
    private String degree;
    private String Date;
    private String id;





    public Scholarship(String title, String organizer, String description, String cgpa, String province, String degree, String Date, String id) {
        Title = title;
        this.organizer = organizer;
        Description = description;
        this.cgpa = cgpa;
        Province = province;
        this.degree = degree;
        this.Date = Date;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

