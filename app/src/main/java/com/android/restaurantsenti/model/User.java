package com.android.restaurantsenti.model;

public class User {
    private int id;
    private String fullName, userName;
    private String email;
    private String password;

    public User(int id, String fullName, String userName, String email, String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public User(String fullName, String userName, String email, String password) {
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }
    public User(User user){
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.userName = user.userName;
        this.password = user.getPassword();
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
