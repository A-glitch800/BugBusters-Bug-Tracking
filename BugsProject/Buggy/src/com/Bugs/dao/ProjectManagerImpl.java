package com.Bugs.dao;

import com.Bugs.beans.Bug;
import com.Bugs.beans.Developer;

import java.sql.*;

public class ProjectManagerImpl implements ProjectManagerDAO{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ProjectBug";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // Receives a report from a tester, returning the bug object
    @Override
    public Bug receiveReport(Bug bug) {
        String insertBugSQL = "INSERT INTO bugs (bug_title, description, severity, status, assigned_to, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(insertBugSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, bug.getBugTitle());
            preparedStatement.setString(2, bug.getDescription());
            preparedStatement.setString(3, bug.getSeverity());
            preparedStatement.setString(4, "Open");
            preparedStatement.setNull(5, Types.INTEGER);  // No developer assigned yet
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert the bug report, no rows affected.");
            }

            // Retrieve the generated bug ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bug.setBugId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Failed to retrieve the bug ID.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bug;
    }

    // Assign a bug to a developer, returns the updated bug object
    @Override
    public Bug assignBug(Bug bug, Developer developer) {
        String updateBugSQL = "UPDATE bugs SET assigned_to = ? WHERE bug_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(updateBugSQL)) {

            preparedStatement.setLong(1, developer.getUserId());
            preparedStatement.setLong(2, bug.getBugId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to assign the bug, no rows affected.");
            }

//            bug.AssignedTo(developer);  // Update the bug object in memory

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bug;
    }

    // Closes a bug, returns the updated bug object
    @Override
    public Bug closeBug(Bug bug) {
        String updateBugSQL = "UPDATE bugs SET status = 'Closed' WHERE bug_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(updateBugSQL)) {

            preparedStatement.setLong(1, bug.getBugId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to close the bug, no rows affected.");
            }

            bug.setBugStatus(false); // Update the bug object in memory

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bug;
    }

    @Override
    public void createNewProject() {

    }

//    @Override
//    public void setBugSeverity() {
//
//    }

    @Override
    public void assignToTeam() {

    }
}
