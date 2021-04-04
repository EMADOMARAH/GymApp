package com.warbugs.gym.Network.RequiestModels;

public class RegisterModel {
    private String email;
    private String password;
    private String password_confirmation;
    private String firstname;
    private String lastname;
    private String name;
    private String phone;
    private String photo;
    private int gender;
    private String birthdate;

    public RegisterModel(String email, String password, String password_confirmation, String firstname, String lastname, String name, String phone, String photo, int gender, String birthdate) {
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}

