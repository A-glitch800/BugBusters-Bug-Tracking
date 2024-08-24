package com.Bugs.dao;

import com.Bugs.Entity.Bug;
import com.Bugs.Exceptions.NoBugExistsException;
import com.Bugs.Exceptions.SqlAccesErrorException;

import java.sql.SQLException;
import java.util.List;

public interface BugDAO {
    void addBug(Bug bug) throws SQLException, SqlAccesErrorException;

    Bug getBugById(int bugId) throws SQLException, NoBugExistsException;

    List<Bug> getBugsByProject(int projectId) throws SQLException, NoBugExistsException;

    List<Bug> getBugsByTester(int testerId) throws SQLException, NoBugExistsException;

    List<Bug> getBugsAssignedToDeveloper(int developerId) throws SQLException, NoBugExistsException;

    void updateBug(Bug bug) throws SQLException, NoBugExistsException;

    void deleteBug(int bugId) throws SQLException, NoBugExistsException;
}

