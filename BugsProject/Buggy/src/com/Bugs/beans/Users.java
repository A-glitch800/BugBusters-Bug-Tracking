package com.Bugs.beans;

abstract public class  Users {
    long userId;
    String userName;
    String email;
    String userType;


    public Users(long userId, String name, String email, String userType) {
        this.userId = userId;
        this.userName = name;
        this.email = email;
        this.userType = userType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
