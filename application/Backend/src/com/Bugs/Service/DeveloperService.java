package com.Bugs.Service;

import com.Bugs.dao.BugDAOImpl;
import com.Bugs.dao.ProjectDAOImpl;
import com.Bugs.Entity.Bug;
import com.Bugs.Entity.Project;
import com.Bugs.Exceptions.DeveloperServiceLayerException;
import com.Bugs.Exceptions.NoBugExistsException;
import com.Bugs.Exceptions.ProjectDaoException;

import java.sql.SQLException;
import java.util.List;

public class DeveloperService {
    private ProjectDAOImpl projectDAO;
    private BugDAOImpl bugDAO;

    public DeveloperService(ProjectDAOImpl projectDAO, BugDAOImpl bugDAO) {
        this.projectDAO = projectDAO;
        this.bugDAO = bugDAO;
    }

    public List<Project> getAssignedProjects(int developerId) throws DeveloperServiceLayerException {
        try {
            // Retrieve all projects assigned to the developer
            return projectDAO.getProjectsForDeveloper(developerId);
        } catch ( ProjectDaoException e) {
            throw new DeveloperServiceLayerException("Failed to retrieve assigned projects", e);
        }
    }

    public List<Bug> getBugsAssignedToDeveloper(int developerId) throws DeveloperServiceLayerException {
        try {
            // Retrieve all bugs assigned to the developer
            return bugDAO.getBugsAssignedToDeveloper(developerId);
        } catch (NoBugExistsException e) {
            throw new DeveloperServiceLayerException("Failed to retrieve bugs assigned to developer", e);
        }
    }

    public void markBugAsResolved(int bugId) throws DeveloperServiceLayerException {
        try {
            // Mark a bug as resolved
            Bug bug = bugDAO.getBugById(bugId);
            if (bug != null && "Open".equals(bug.getStatus())) {
                bug.setStatus("Resolved");
                bugDAO.updateBug(bug);
            } else {
                throw new DeveloperServiceLayerException("Bug is either not found or already closed.");
            }
        } catch (NoBugExistsException e) {
            throw new DeveloperServiceLayerException("Failed to mark bug as resolved", e);
        }
    }
}

