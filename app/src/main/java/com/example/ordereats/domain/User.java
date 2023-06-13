package com.example.ordereats.domain;

public class User {

    private int user_Id;
    private String user_Name;
    private String user_Lastname;
    private String user_Phone;
    private String user_Email;
    private String user_Password;

    private int user_Active;

    public User() {
    }

    public User(int user_Id, String user_Name, String user_Lastname, String user_Phone, String user_Email, String user_Password, int user_Active) {
        this.user_Id = user_Id;
        this.user_Name = user_Name;
        this.user_Lastname = user_Lastname;
        this.user_Phone = user_Phone;
        this.user_Email = user_Email;
        this.user_Password = user_Password;
        this.user_Active = user_Active;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Lastname() {
        return user_Lastname;
    }

    public void setUser_Lastname(String user_Lastname) {
        this.user_Lastname = user_Lastname;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public int getUser_Active() {
        return user_Active;
    }

    public void setUser_Active(int user_Active) {
        this.user_Active = user_Active;
    }
}
