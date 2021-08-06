package com.example.cscb07summer.profile;

public class DoctorHelperClass {

    String name, email, gender, birthday, specialization;

    public DoctorHelperClass(String name, String email, String gender, String birthday, String specialization) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.specialization = specialization;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
