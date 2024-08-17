package com.Bugs.beans;
import java.util.Date;

public class Bug {
    long bugId;
    String bugTitle;
    String severity;
    String description;
    boolean bugStatus;
    long projectId;
    long TesterId;
    Date createdOn;

    public Tester createdBy(Tester tester){
        return null;
    }

    public Date createdOn(){
        return null;}

    public long getBugId() {
        return bugId;
    }

    public Bug() {
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Bug(long bugId, String bugTitle, String severity, String description, boolean bugStatus, long projectId, long testerId, Date createdOn) {
        this.bugId = bugId;
        this.bugTitle = bugTitle;
        this.severity = severity;
        this.description = description;
        this.bugStatus = bugStatus;
        this.projectId = projectId;
        TesterId = testerId;
        this.createdOn = createdOn;
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

    @Override
    public String toString() {
        return "Bug{" +
                "bugId=" + bugId +
                ", bugTitle='" + bugTitle + '\'' +
                ", severity='" + severity + '\'' +
                ", description='" + description + '\'' +
                ", bugStatus=" + bugStatus +
                ", projectId=" + projectId +
                ", TesterId=" + TesterId +
                ", createdOn=" + createdOn +
                '}';
    }
}