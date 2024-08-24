package com.Bugs.dao;

import com.Bugs.Entity.Bug;
import com.Bugs.Exceptions.NoBugExistsException;
import com.Bugs.Exceptions.SqlAccesErrorException;

import java.sql.*;
import java.util.*;

public class BugDAOImpl implements BugDAO {
    private Connection connection;

    public BugDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addBug(Bug bug) throws SqlAccesErrorException {
        String sql = "INSERT INTO Bugs (projectId, title, description, severity, status, createdBy, createdOn, assignedTo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bug.getProjectId());
            stmt.setString(2, bug.getTitle());
            stmt.setString(3, bug.getDescription());
            stmt.setString(4, bug.getSeverity());
            stmt.setString(5, bug.getStatus());
            stmt.setInt(6, bug.getCreatedBy());
            stmt.setTimestamp(7, bug.getCreatedOn());
            stmt.setObject(8, bug.getAssignedTo()); // Handle null values for assignedTo
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccesErrorException("Error occurred while adding the bug to the database.", e);
        }
    }

    @Override
    public Bug getBugById(int bugId) throws NoBugExistsException {
        String sql = "SELECT * FROM Bugs WHERE bugId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bugId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bug bug = new Bug();
                bug.setBugId(rs.getInt("bugId"));
                bug.setProjectId(rs.getInt("projectId"));
                bug.setTitle(rs.getString("title"));
                bug.setDescription(rs.getString("description"));
                bug.setSeverity(rs.getString("severity"));
                bug.setStatus(rs.getString("status"));
                bug.setCreatedBy(rs.getInt("createdBy"));
                bug.setCreatedOn(rs.getTimestamp("createdOn"));
                bug.setAssignedTo((Integer) rs.getObject("assignedTo"));
                return bug;
            } else {
                throw new NoBugExistsException("No bug found with ID: " + bugId);
            }
        } catch (SQLException e) {
            throw new NoBugExistsException("Error occurred while retrieving the bug with ID: " + bugId, e);
        }
    }

    @Override
    public List<Bug> getBugsByProject(int projectId) throws NoBugExistsException {
        String sql = "SELECT * FROM Bugs WHERE projectId = ?";
        List<Bug> bugs = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bug bug = new Bug();
                bug.setBugId(rs.getInt("bugId"));
                bug.setProjectId(rs.getInt("projectId"));
                bug.setTitle(rs.getString("title"));
                bug.setDescription(rs.getString("description"));
                bug.setSeverity(rs.getString("severity"));
                bug.setStatus(rs.getString("status"));
                bug.setCreatedBy(rs.getInt("createdBy"));
                bug.setCreatedOn(rs.getTimestamp("createdOn"));
                bug.setAssignedTo((Integer) rs.getObject("assignedTo"));
                bugs.add(bug);
            }
        } catch (SQLException e) {
            throw new NoBugExistsException("Error occurred while retrieving bugs for the project with ID: " + projectId, e);
        }
        if (bugs.isEmpty()) {
            throw new NoBugExistsException("No bugs found for the project with ID: " + projectId);
        }
        return bugs;
    }

    @Override
    public List<Bug> getBugsByTester(int testerId) throws NoBugExistsException {
        String sql = "SELECT * FROM Bugs WHERE createdBy = ?";
        List<Bug> bugs = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, testerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bug bug = new Bug();
                bug.setBugId(rs.getInt("bugId"));
                bug.setProjectId(rs.getInt("projectId"));
                bug.setTitle(rs.getString("title"));
                bug.setDescription(rs.getString("description"));
                bug.setSeverity(rs.getString("severity"));
                bug.setStatus(rs.getString("status"));
                bug.setCreatedBy(rs.getInt("createdBy"));
                bug.setCreatedOn(rs.getTimestamp("createdOn"));
                bug.setAssignedTo((Integer) rs.getObject("assignedTo"));
                bugs.add(bug);
            }
        } catch (SQLException e) {
            throw new NoBugExistsException("Error occurred while retrieving bugs created by tester with ID: " + testerId, e);
        }
        if (bugs.isEmpty()) {
            throw new NoBugExistsException("No bugs found created by tester with ID: " + testerId);
        }
        return bugs;
    }

    @Override
    public List<Bug> getBugsAssignedToDeveloper(int developerId) throws NoBugExistsException {
        String sql = "SELECT * FROM Bugs WHERE assigned_to = ?";
        List<Bug> bugs = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, developerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Bug bug = new Bug();
                    bug.setBugId(resultSet.getInt("bug_id"));
                    bug.setTitle(resultSet.getString("title"));
                    bug.setDescription(resultSet.getString("description"));
                    bug.setSeverity(resultSet.getString("severity_level"));
                    bug.setStatus(resultSet.getString("status"));
                    bug.setCreatedBy(resultSet.getInt("created_by"));
                    bug.setCreatedOn(resultSet.getTimestamp("created_on"));
                    bug.setAssignedTo(resultSet.getInt("assigned_to"));
                    bug.setProjectId(resultSet.getInt("project_id"));

                    bugs.add(bug);
                }
            }
        } catch (SQLException e) {
            throw new NoBugExistsException("Error occurred while retrieving bugs assigned to developer with ID: " + developerId, e);
        }

        if (bugs.isEmpty()) {
            throw new NoBugExistsException("No bugs found assigned to developer with ID: " + developerId);
        }

        return bugs;
    }

    @Override
    public void updateBug(Bug bug) throws NoBugExistsException {
        String sql = "UPDATE Bugs SET projectId = ?, title = ?, description = ?, severity = ?, status = ?, assignedTo = ? WHERE bugId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bug.getProjectId());
            stmt.setString(2, bug.getTitle());
            stmt.setString(3, bug.getDescription());
            stmt.setString(4, bug.getSeverity());
            stmt.setString(5, bug.getStatus());
            stmt.setObject(6, bug.getAssignedTo()); // Handle null values for assignedTo
            stmt.setInt(7, bug.getBugId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new NoBugExistsException("No bug found with ID: " + bug.getBugId());
            }
        } catch (SQLException e) {
            throw new NoBugExistsException("Error occurred while updating the bug with ID: " + bug.getBugId(), e);
        }
    }

    @Override
    public void deleteBug(int bugId) throws NoBugExistsException {
        String sql = "DELETE FROM Bugs WHERE bugId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bugId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new NoBugExistsException("No bug found with ID: " + bugId);
            }
        } catch (SQLException e) {
            throw new NoBugExistsException("Error occurred while deleting the bug with ID: " + bugId, e);
        }
    }
}

