package com.Bugs.dao;

import com.Bugs.Entity.User;
import com.Bugs.Exceptions.NoUserExistsException;

import java.sql.SQLException;

public interface UserDAO {
    void addUser(User user) throws SQLException, NoUserExistsException;

    User getUserById(int userId) throws SQLException, NoUserExistsException;

    void updateUser(User user) throws SQLException, NoUserExistsException;

    void deleteUser(int userId) throws SQLException, NoUserExistsException;
}
