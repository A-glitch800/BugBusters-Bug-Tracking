package com.Bugs.dao;

import com.Bugs.Entity.User;
import com.Bugs.Exceptions.NoUserExistsException;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User user) throws NoUserExistsException {
        String sql = "INSERT INTO Users (username, email, role, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new NoUserExistsException("Failed to add user", e);
        }
    }

    @Override
    public User getUserById(int userId) throws NoUserExistsException {
        String sql = "SELECT * FROM Users WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                return user;
            } else {
                throw new NoUserExistsException("No user found with ID: " + userId);
            }
        } catch (SQLException e) {
            throw new NoUserExistsException("Failed to get user by ID", e);
        }
    }

    @Override
    public void updateUser(User user) throws NoUserExistsException {
        String sql = "UPDATE Users SET username = ?, email = ?, role = ?, password = ? WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getUserId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new NoUserExistsException("No user found with ID: " + user.getUserId());
            }
        } catch (SQLException e) {
            throw new NoUserExistsException("Failed to update user", e);
        }
    }

    @Override
    public void deleteUser(int userId) throws NoUserExistsException {
        String sql = "DELETE FROM Users WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new NoUserExistsException("No user found with ID: " + userId);
            }
        } catch (SQLException e) {
            throw new NoUserExistsException("Failed to delete user", e);
        }
    }
}

