package com.covid19.odisha;

public class unaffected {
    String id;
    String Mobile;
    String Name;
    String Age;
    String Sex;
    String Blood;
    String Emergency;
    String Condition;



    public unaffected(String ID, String mobile, String name, String age, String sex, String blood, String emergency, String condition) {

        this.id=ID;
        this.Mobile = mobile;
        this.Name = name;
        this.Age = age;
        this.Sex = sex;
        this.Blood = blood;
        this.Emergency = emergency;
        this.Condition = condition;
    }

    public String getId() {
        return id;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getName() {
        return Name;
    }

    public String getAge() {
        return Age;
    }

    public String getSex() {
        return Sex;
    }

    public String getBlood() {
        return Blood;
    }

    public String getEmergency() {
        return Emergency;
    }

    public String getCondition() {
        return Condition;
    }
}

