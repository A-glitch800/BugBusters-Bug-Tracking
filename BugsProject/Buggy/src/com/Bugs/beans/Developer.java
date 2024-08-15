package com.Bugs.beans;

public class Developer extends Users {

    long projectID;

    public Developer(long userId,String userName,String email,String userType){
        super(userId,userName,email,userType);
    }

    public void viewProject(long projectID){
        //return the projectId
    }

    public void fixBug(){
        //fixes the bug
    }


    public void markClose(){
        // applies to the project manager to fix the bug status
    }

    public void validateMarkClose(){
        // validates if the bug is fixed
    }


}
