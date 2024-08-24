package com.Bugs.dao;

import com.Bugs.Entity.Project;
import com.Bugs.Entity.User;
import com.Bugs.Exceptions.NoprojectExistsException;
import com.Bugs.Exceptions.ProjectDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ProjectDAO {
    void addProject(Project project) throws SQLException, ProjectDaoException;

    int createProject(String projectName, Date startDate, int managerId) throws SQLException, ProjectDaoException;

    Project getProjectById(int projectId) throws SQLException, ProjectDaoException;

    List<Project> getAllProjects() throws SQLException, ProjectDaoException;

    List<Project> getAllProjectsForManager(int managerId) throws SQLException, ProjectDaoException;

    List<Project> getProjectsForDeveloper(int developerId) throws SQLException, ProjectDaoException;

    List<Project> getProjectsForTester(int testerId) throws SQLException, ProjectDaoException;

    // Method to check if a tester is assigned to a project
    boolean isTesterAssignedToProject(int testerId, int projectId) throws SQLException, ProjectDaoException;

    String getProjectStatusById(int projectId) throws SQLException, ProjectDaoException;

    void assignUserToProject(int projectId, int userId, String role) throws SQLException, ProjectDaoException;

    default List<User> getTeamMembersByProjectId(int projectId) throws SQLException {
        String sql = "SELECT u.user_id, u.name, u.email, u.role " +
                "FROM Users u " +
                "JOIN ProjectAssignments pa ON u.user_id = pa.user_id " +
                "WHERE pa.project_id = ?";
        List<User> teamMembers = new ArrayList<>();

        Connection connection = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("role"));

                    teamMembers.add(user);
                }
            }
        }

        return teamMembers;
    }

    void updateProject(Project project) throws SQLException, ProjectDaoException;

    void deleteProject(int projectId) throws SQLException, NoprojectExistsException;

    int countProjectsByDeveloper(int developerId) throws SQLException, ProjectDaoException;

    int countProjectsByTester(int testerId) throws SQLException, ProjectDaoException;

    int countProjectsByManager(int managerId) throws SQLException, ProjectDaoException;

    boolean isStartDateValid(Date startDate);
}
