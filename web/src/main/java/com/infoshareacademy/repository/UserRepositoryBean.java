package com.infoshareacademy.repository;

import com.infoshareacademy.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserRepositoryBean {

    @PersistenceContext
    private EntityManager entityManager;


    public Optional<User> findUserById(Long userId) {

        return Optional.ofNullable(entityManager.find(User.class, userId));
    }


    public void save(User user) {
        entityManager.persist(user);
    }

    public User update(User user) {

        return entityManager.merge(user);
    }

    public Optional<User> findByEmail(String email) {
        Query query = entityManager.createNamedQuery("User.findByEmail");
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }

    public List<User> findAll() {
        Query query = entityManager.createNamedQuery("User.findAll");
        return query.getResultList();
    }
}
