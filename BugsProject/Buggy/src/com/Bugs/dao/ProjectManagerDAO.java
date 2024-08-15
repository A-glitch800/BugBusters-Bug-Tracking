package com.Bugs.dao;

public interface ProjectManagerDAO {
    void receiveReport();

    void assignBug();

    void closeBug();

    void createNewProject();

    void setBugSeverity();

    void assignToTeam();
}
