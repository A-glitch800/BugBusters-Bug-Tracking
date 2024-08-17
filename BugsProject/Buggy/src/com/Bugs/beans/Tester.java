package com.Bugs.beans;

import com.Bugs.beans.Bug;
import com.Bugs.dao.ProjectManagerDAO;
import com.Bugs.dao.ProjectManagerImpl;
import com.Bugs.dao.TesterDAO;
import com.Bugs.dao.TesterDAOImpl;

import java.util.ArrayList;
import java.util.Date;

public class Tester extends Users {

    Bug bug;
    private ArrayList<Bug> bugList;

    public Tester(long userId, String userName, String email, String userType) {
        super(userId,userName,email,userType);
        bugList = new ArrayList<>();
    }

    public TesterDAO testerDAO = new TesterDAOImpl();

    public void createBug(long bugId, String bugTitle, String severity, String description, boolean bugStatus, long projectId, long testerId, Date createdOn){
        // raises the bug request
        bug = testerDAO.createBugs(bugId, bugTitle, severity, description, bugStatus, projectId, testerId, createdOn);
        bugList.add(bug);
    }

    public void setBugSeverity(long bugId, String severity){
        //sets the severity of the bug
        for(Bug bug: bugList) {
            if(bug.getBugId() == bugId) {
                testerDAO.setBugSeverity(bug, severity);
            }
        }
    }

    public void fileReport(){
        // filing the report of the bug

    }

    public void checkStatus(){
        // checking the status of the bugs

    }

}
