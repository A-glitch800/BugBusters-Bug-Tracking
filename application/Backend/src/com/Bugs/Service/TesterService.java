package com.Bugs.Service;

import com.Bugs.dao.BugDAOImpl;
import com.Bugs.dao.ProjectDAOImpl;
import com.Bugs.Entity.Bug;
import com.Bugs.Entity.Project;
import com.Bugs.Exceptions.NoBugExistsException;
import com.Bugs.Exceptions.ProjectDaoException;
import com.Bugs.Exceptions.SqlAccesErrorException;
import com.Bugs.Exceptions.TesterServiceLayerException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TesterService {
    private ProjectDAOImpl projectDAO;
    private BugDAOImpl bugDAO;

    public TesterService(ProjectDAOImpl projectDAO, BugDAOImpl bugDAO) {
        this.projectDAO = projectDAO;
        this.bugDAO = bugDAO;
    }

    public List<Project> getAssignedProjects(int testerId) throws TesterServiceLayerException {
        try {
            // Retrieve all projects assigned to the tester
            return projectDAO.getProjectsForTester(testerId);
        } catch (ProjectDaoException e) {
            throw new TesterServiceLayerException("Failed to retrieve projects for tester", e);
        }
    }

    public List<Bug> getBugsCreatedByTester(int testerId) throws TesterServiceLayerException {
        try {
            // Retrieve all bugs created by the tester
            return bugDAO.getBugsByTester(testerId);
        } catch (NoBugExistsException e) {
            throw new TesterServiceLayerException("Failed to retrieve bugs created by tester", e);
        }
    }

    public void createBug(int testerId, int projectId, String title, String description, String severity) throws TesterServiceLayerException {
        try {
            // Validate that the tester is assigned to the project
            boolean isAssigned = projectDAO.isTesterAssignedToProject(testerId, projectId);
            if (!isAssigned) {
                throw new IllegalArgumentException("Tester is not assigned to the specified project.");
            }

            // Validate project status
            String projectStatus = projectDAO.getProjectStatusById(projectId);
            if (!"In Progress".equals(projectStatus)) {
                throw new IllegalArgumentException("Bugs can only be reported for projects with status 'In Progress'.");
            }

            // Create the bug
            Bug bug = new Bug();
            bug.setProjectId(projectId);
            bug.setTitle(title);
            bug.setDescription(description);
            bug.setSeverity(severity);
            bug.setStatus("Open");
            bug.setCreatedBy(testerId);
            bug.setCreatedOn(new java.sql.Timestamp(new Date().getTime()));
            bug.setAssignedTo(null); // Initially, no developer assigned

            bugDAO.addBug(bug);
        } catch (SqlAccesErrorException | ProjectDaoException e) {
            throw new TesterServiceLayerException("Failed to create bug", e);
        }
    }
}

