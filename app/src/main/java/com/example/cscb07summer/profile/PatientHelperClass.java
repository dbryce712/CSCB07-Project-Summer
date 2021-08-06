package com.example.cscb07summer.profile;

public class PatientHelperClass {

    String name, email, gender, birth, condition;

    public PatientHelperClass() {
    }

    public PatientHelperClass(String name, String email, String gender, String birth, String condition) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
