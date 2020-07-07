package com.covid19.odisha;

public class forumdb {
    String id;
    String age;
    String gender;
    String symptoms;
    String condition;
    String treatment;
    String con;
    String info;
    String date;
    String time;
    String email;

    public forumdb(String ID, String AGE, String GENDER, String SYMPTOMS, String CONDITION, String TREATMENT, String CON, String INFO, String DATE, String TIME, String EMAIL){
        this.id=ID;
        this.age=AGE;
        this.gender=GENDER;
        this.symptoms=SYMPTOMS;
        this.condition=CONDITION;
        this.treatment=TREATMENT;
        this.con=CON;
        this.info=INFO;
        this.date=DATE;
        this.time=TIME;
        this.email=EMAIL;
    }

    public String getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getCondition() {
        return condition;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getCon() {
        return con;
    }

    public String getInfo() {
        return info;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getEmail() {
        return email;
    }
}
