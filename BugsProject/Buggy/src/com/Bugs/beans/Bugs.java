package com.Bugs.beans;
import java.util.Date;

public class Bugs {
    long bugId;
    String bugTitle;
    String severity;
    String description;
    boolean bugStatus;
    long projectId;
    long TesterId;

    public Tester createdBy(Tester tester){
        return null;
    }

    public Date createdOn(){
        return null;}

    public long getBugId() {
        return bugId;
    }

    public void setBugId(long bugId) {
        this.bugId = bugId;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(boolean bugStatus) {
        this.bugStatus = bugStatus;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getTesterId() {
        return TesterId;
    }

    public void setTesterId(long testerId) {
        TesterId = testerId;
    }
}