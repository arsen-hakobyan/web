package com.backend.admin.db;

import com.backend.admin.entity.User;

import java.util.List;

public interface UserDAO {

    public User getById(Long id);

    public void persist(User user);

    public void delete(User user);

    public void merge(User user);

    List<User> getAll();
}
