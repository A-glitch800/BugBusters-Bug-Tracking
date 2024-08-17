package com.Bugs.beans;


import com.Bugs.dao.ProjectManagerDAO;
import com.Bugs.dao.ProjectManagerImpl;

public class ProjectManager extends Users {

    public ProjectManager(long userId, String userName, String email) {
        super(userId,userName,email,"Project Manager");
    }

    public ProjectManagerDAO projectManagerDAO = new ProjectManagerImpl();

    public Bug receiveReport(Bug bug) {
        // Forward the bug to the DAO to process the report
        return projectManagerDAO.receiveReport(bug);
    }

    public Bug assignBug(Bug bug, Developer developer) {
        // Forward the bug and developer to the DAO to assign the bug
        return projectManagerDAO.assignBug(bug, developer);
    }

    public Bug closeBug(Bug bug) {
        // Forward the bug to the DAO to close the bug
        return projectManagerDAO.closeBug(bug);
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
