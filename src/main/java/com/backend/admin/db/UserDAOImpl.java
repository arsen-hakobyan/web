package com.backend.admin.db;

import com.backend.admin.entity.User;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
    @PersistenceContext(unitName = "adminPersistenceUnit")
    protected EntityManager em;
    private StandardPBEStringEncryptor encoder;


    @Resource(name = "encryptor")
    @Required
    public void setEncryption(StandardPBEStringEncryptor encoder) {
        this.encoder = encoder;
    }


    @Override
    public User getById(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public void persist(User user) {
    }

    @Override
    public void merge(User user) {
        em.merge(user);
    }

    @Override
    public List<User> getAll() {
        Query q = em.createQuery("from user ");
        return q.getResultList();
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }


}
