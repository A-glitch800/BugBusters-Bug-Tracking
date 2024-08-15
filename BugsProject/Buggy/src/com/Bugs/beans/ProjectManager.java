package com.Bugs.beans;

public class ProjectManager extends Users {

    public ProjectManager(long userId, String userName, String email, String userType) {
        super(userId,userName,email,userType);
    }

    public void receiveReport(){
        // to receive the report
    }

    public void assignBug(){
        //to assign the bug to the developer
    }

    public void closeBug(){
        // to close the bug
    }

    public void createNewProject(){
        //creates new project
    }

//    public void setBugSeverity(){
//        //sets the severity of the bug
//    }

    public void assignToTeam(){
        // assigns bugs to the project teams
    }
}
