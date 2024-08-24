package com.Bugs.Service;

import com.Bugs.dao.BugDAOImpl;
import com.Bugs.dao.ProjectDAOImpl;
import com.Bugs.Entity.Bug;
import com.Bugs.Entity.Project;
import com.Bugs.Exceptions.NoBugExistsException;
import com.Bugs.Exceptions.NoprojectExistsException;
import com.Bugs.Exceptions.ProjectDaoException;
import com.Bugs.Exceptions.ProjectManagerServiceLayerException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProjectManagerService {
    private ProjectDAOImpl projectDAO;
    private BugDAOImpl bugDAO;

    public ProjectManagerService(ProjectDAOImpl projectDAO, BugDAOImpl bugDAO) {
        this.projectDAO = projectDAO;
        this.bugDAO = bugDAO;
    }

    public List<Project> getAllProjects(int managerId) throws ProjectManagerServiceLayerException {
        try {
            // Retrieve all projects managed by the project manager
            return projectDAO.getAllProjectsForManager(managerId);
        } catch (ProjectDaoException e) {
            throw new ProjectManagerServiceLayerException("Failed to retrieve projects for manager", e);
        }
    }

    public void createProject(String projectName, Date startDate, int managerId, List<Integer> developerIds, List<Integer> testerIds) throws ProjectManagerServiceLayerException {
        try {
            // Perform validation and create project
            if (startDate.before(new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000))) {
                throw new IllegalArgumentException("Start date should be at least 2 days later than the current date.");
            }

            // Validate project manager's project limit
            int managerProjectsCount = projectDAO.countProjectsByManager(managerId);
            if (managerProjectsCount >= 4) {
                throw new IllegalArgumentException("Project manager can manage a maximum of 4 projects.");
            }

            // Create the project
            int projectId = projectDAO.createProject(projectName, startDate, managerId);

            // Assign developers to the project
            for (int developerId : developerIds) {
                int developerProjectsCount = projectDAO.countProjectsByDeveloper(developerId);
                if (developerProjectsCount >= 1) {
                    throw new IllegalArgumentException("Developer can be assigned to only one project.");
                }
                projectDAO.assignUserToProject(projectId, developerId, "Developer");
            }

            // Assign testers to the project
            for (int testerId : testerIds) {
                int testerProjectsCount = projectDAO.countProjectsByTester(testerId);
                if (testerProjectsCount >= 2) {
                    throw new IllegalArgumentException("Tester can be assigned to a maximum of 2 projects.");
                }
                projectDAO.assignUserToProject(projectId, testerId, "Tester");
            }
        } catch (ProjectDaoException e) {
            throw new ProjectManagerServiceLayerException("Failed to create project", e);
        }
    }

    public void updateProject(Project project) throws ProjectManagerServiceLayerException {
        try {
            // Perform validation and update project
            projectDAO.updateProject(project);
        } catch (ProjectDaoException e) {
            throw new ProjectManagerServiceLayerException("Failed to update project", e);
        }
    }

    public void deleteProject(int projectId) throws ProjectManagerServiceLayerException {
        try {
            // Delete a project
            projectDAO.deleteProject(projectId);
        } catch (NoprojectExistsException e) {
            throw new ProjectManagerServiceLayerException("Failed to delete project", e);
        }
    }

    public List<Bug> getAllBugsForProject(int projectId) throws ProjectManagerServiceLayerException {
        try {
            // Retrieve all bugs associated with a project
            return bugDAO.getBugsByProject(projectId);
        } catch (NoBugExistsException e) {
            throw new ProjectManagerServiceLayerException("Failed to retrieve bugs for project", e);
        }
    }

    public void assignBug(int bugId, int developerId) throws ProjectManagerServiceLayerException {
        try {
            // Assign a bug to a developer
            Bug bug = bugDAO.getBugById(bugId);
            if (bug != null) {
                bug.setAssignedTo(developerId);
                bugDAO.updateBug(bug);
            }
        } catch (NoBugExistsException e) {
            throw new ProjectManagerServiceLayerException("Failed to assign bug", e);
        }
    }

    public void closeBug(int bugId) throws ProjectManagerServiceLayerException {
        try {
            // Close a bug
            Bug bug = bugDAO.getBugById(bugId);
            if (bug != null) {
                bug.setStatus("Closed");
                bugDAO.updateBug(bug);
            }
        } catch ( NoBugExistsException e) {
            throw new ProjectManagerServiceLayerException("Failed to close bug", e);
        }
    }
}

