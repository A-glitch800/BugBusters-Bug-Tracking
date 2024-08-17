package com.Bugs.dao;

import com.Bugs.beans.Bug;

import java.util.Date;

public class TesterDAOImpl implements TesterDAO{
    @Override
    public Bug createBugs(long bugId, String bugTitle, String severity, String description, boolean bugStatus, long projectId, long testerId, Date createdOn){
        // raises the bug request
        Bug bug = new Bug(bugId, bugTitle, severity, description, bugStatus, projectId, testerId, createdOn);
        return bug;
    }

    @Override
    public void setBugSeverity(Bug bug, String severity) {
        bug.setSeverity(severity);
    }

    @Override
    public void fileReport() {

    }

    @Override
    public void checkStatus() {

    }
}
