package com.example.ems;

public class Application {
    private String Name;
    private String CNIC;
    private String Status;
    private String CGPA;
    private String Degree;

    private String id;


    public Application(String Name, String CNIC, String status, String CGPA, String degree, String id) {
        this.Name = Name;
        this.CNIC = CNIC;
        Status = status;
        this.CGPA = CGPA;
        Degree = degree;
        this.id = id;
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
        this.Name = name;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCGPA() {
        return CGPA;
    }

    public void setCGPA(String CGPA) {
        this.CGPA = CGPA;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }
}
