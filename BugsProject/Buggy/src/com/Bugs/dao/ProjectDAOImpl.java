package com.Bugs.dao;

import com.Bugs.Entity.Project;
import com.Bugs.Exceptions.NoprojectExistsException;
import com.Bugs.Exceptions.ProjectDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {
    private Connection connection;

    public ProjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addProject(Project project) throws ProjectDaoException {
        String sql = "INSERT INTO Projects (projectName, startDate, status, projectManagerId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setString(3, project.getStatus());
            stmt.setInt(4, project.getProjectManagerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to add project", e);
        }
    }

    @Override
    public int createProject(String projectName, Date startDate, int managerId) throws ProjectDaoException {
        String sql = "INSERT INTO Projects (project_name, start_date, project_manager_id, status) VALUES (?, ?, ?, 'In Progress')";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, projectName);
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setInt(3, managerId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating project failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated project ID
                } else {
                    throw new SQLException("Creating project failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to create project", e);
        }
    }

    @Override
    public Project getProjectById(int projectId) throws ProjectDaoException {
        String sql = "SELECT * FROM Projects WHERE projectId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setStartDate(rs.getDate("startDate"));
                project.setStatus(rs.getString("status"));
                project.setProjectManagerId(rs.getInt("projectManagerId"));
                return project;
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to get project by ID", e);
        }
        return null;
    }

    @Override
    public List<Project> getAllProjects() throws ProjectDaoException {
        String sql = "SELECT * FROM Projects";
        List<Project> projects = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setStartDate(rs.getDate("startDate"));
                project.setStatus(rs.getString("status"));
                project.setProjectManagerId(rs.getInt("projectManagerId"));
                projects.add(project);
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to get all projects", e);
        }
        return projects;
    }

    @Override
    public List<Project> getAllProjectsForManager(int managerId) throws ProjectDaoException {
        String sql = "SELECT * FROM Projects WHERE project_manager_id = ?";
        List<Project> projects = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, managerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project();
                    project.setProjectId(resultSet.getInt("project_id"));
                    project.setProjectName(resultSet.getString("project_name"));
                    project.setStartDate(resultSet.getDate("start_date"));
                    project.setStatus(resultSet.getString("status"));
                    project.setProjectManagerId(resultSet.getInt("project_manager_id"));

                    project.setTeamMembers(getTeamMembersByProjectId(project.getProjectId()));

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to get all projects for manager", e);
        }

        return projects;
    }

    @Override
    public List<Project> getProjectsForDeveloper(int developerId) throws ProjectDaoException {
        String sql = "SELECT p.* FROM Projects p " +
                "JOIN ProjectAssignments pa ON p.project_id = pa.project_id " +
                "WHERE pa.user_id = ? AND EXISTS " +
                "(SELECT 1 FROM Users u WHERE u.user_id = pa.user_id AND u.role = 'Developer')";
        List<Project> projects = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, developerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project();
                    project.setProjectId(resultSet.getInt("project_id"));
                    project.setProjectName(resultSet.getString("project_name"));
                    project.setStartDate(resultSet.getDate("start_date"));
                    project.setStatus(resultSet.getString("status"));
                    project.setProjectManagerId(resultSet.getInt("project_manager_id"));

                    project.setTeamMembers(getTeamMembersByProjectId(project.getProjectId()));

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to get projects for developer", e);
        }

        return projects;
    }

    @Override
    public List<Project> getProjectsForTester(int testerId) throws ProjectDaoException {
        String sql = "SELECT p.* FROM Projects p " +
                "JOIN ProjectAssignments pa ON p.project_id = pa.project_id " +
                "WHERE pa.user_id = ? AND EXISTS " +
                "(SELECT 1 FROM Users u WHERE u.user_id = pa.user_id AND u.role = 'Tester')";
        List<Project> projects = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, testerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project();
                    project.setProjectId(resultSet.getInt("project_id"));
                    project.setProjectName(resultSet.getString("project_name"));
                    project.setStartDate(resultSet.getDate("start_date"));
                    project.setStatus(resultSet.getString("status"));
                    project.setProjectManagerId(resultSet.getInt("project_manager_id"));

                    project.setTeamMembers(getTeamMembersByProjectId(project.getProjectId()));

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to get projects for tester", e);
        }

        return projects;
    }

    @Override
    public boolean isTesterAssignedToProject(int testerId, int projectId) throws ProjectDaoException {
        String sql = "SELECT COUNT(*) FROM ProjectAssignments WHERE user_id = ? AND project_id = ? AND role = 'Tester'";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, testerId);
            statement.setInt(2, projectId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to check if tester is assigned to project", e);
        }
        return false;
    }

    @Override
    public String getProjectStatusById(int projectId) throws ProjectDaoException {
        String sql = "SELECT status FROM Projects WHERE project_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("status");
                }
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to get project status by ID", e);
        }
        return null;
    }

    @Override
    public void assignUserToProject(int projectId, int userId, String role) throws ProjectDaoException {
        String sql = "INSERT INTO ProjectAssignments (project_id, user_id, role) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projectId);
            statement.setInt(2, userId);
            statement.setString(3, role);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to assign user to project", e);
        }
    }

    @Override
    public void updateProject(Project project) throws ProjectDaoException {
        String sql = "UPDATE Projects SET projectName = ?, startDate = ?, status = ?, projectManagerId = ? WHERE projectId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setString(3, project.getStatus());
            stmt.setInt(4, project.getProjectManagerId());
            stmt.setInt(5, project.getProjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to update project", e);
        }
    }

    @Override
    public void deleteProject(int projectId) throws NoprojectExistsException {
        String sql = "DELETE FROM Projects WHERE projectId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new NoprojectExistsException("Failed to delete project", e);
        }
    }

    @Override
    public int countProjectsByDeveloper(int developerId) throws ProjectDaoException {
        String sql = "SELECT COUNT(*) FROM ProjectAssignments WHERE userId = ? AND role = 'Developer'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, developerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to count projects by developer", e);
        }
        return 0;
    }

    @Override
    public int countProjectsByTester(int testerId) throws ProjectDaoException {
        String sql = "SELECT COUNT(*) FROM ProjectAssignments WHERE userId = ? AND role = 'Tester'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, testerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to count projects by tester", e);
        }
        return 0;
    }

    @Override
    public int countProjectsByManager(int managerId) throws ProjectDaoException {
        String sql = "SELECT COUNT(*) FROM Projects WHERE projectManagerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ProjectDaoException("Failed to count projects by manager", e);
        }
        return 0;
    }

    @Override
    public boolean isStartDateValid(Date startDate) {
        Date today = new Date(System.currentTimeMillis());
        return startDate.after(new Date(today.getTime() + 2 * 24 * 60 * 60 * 1000)); // 2 days later
    }
}

