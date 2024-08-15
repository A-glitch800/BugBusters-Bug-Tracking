package com.Bugs.beans;

import java.util.Date;

public class Project {
    long projectId;
    String projectName;
    Date startDate;
    long managerId;
    String projectStatus;

    // list of team
    // sortable list of bugs




    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerName(long managerId) {
        this.managerId = managerId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}