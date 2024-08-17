package com.Bugs.dao;

import com.Bugs.beans.Bug;
import com.Bugs.beans.Developer;

public interface ProjectManagerDAO {
    Bug receiveReport(Bug bug);

    Bug assignBug(Bug bug, Developer developer);

    Bug closeBug(Bug bug);

    void createNewProject();

    void setBugSeverity();

    void assignToTeam();
}