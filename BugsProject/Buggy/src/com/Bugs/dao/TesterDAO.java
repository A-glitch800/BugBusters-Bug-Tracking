package com.Bugs.dao;

import com.Bugs.beans.Bug;

import java.util.Date;

public interface TesterDAO {
    Bug createBugs(long bugId, String bugTitle, String severity, String description, boolean bugStatus, long projectId, long testerId, Date createdOn);

    void setBugSeverity(Bug bug, String severity);

    void fileReport();

    void checkStatus();
}
