package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Класс реализация интерфейса UserRepository. Реализация методов  для доступа к БД
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    @Autowired
    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * @see UserRepository#getAllUsersByPredicat(User) 
     * @param user
     * @return список пользователей
     */
    @Override
    public List<User> getAllUsersByPredicat(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> UserRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(UserRoot );

        Predicate criteria = criteriaBuilder.conjunction();

        //officeId
        Predicate pr = criteriaBuilder.equal(UserRoot.get("office").get("id"), user.getOffice().getId());
        criteria = criteriaBuilder.and(criteria, pr);

        //firstName
        if (user.getFirstName() != null) {
            Predicate p = criteriaBuilder.equal(UserRoot.get("firstName"), user.getFirstName());
            criteria = criteriaBuilder.and(criteria, p);
        }

        //secondName
        if (user.getSecondName() != null) {
            Predicate p = criteriaBuilder.equal(UserRoot.get("secondName"), user.getSecondName());
            criteria = criteriaBuilder.and(criteria, p);
        }

        //middleName
        if (user.getMiddleName() != null) {
            Predicate p = criteriaBuilder.equal(UserRoot.get("middleName"), user.getMiddleName());
            criteria = criteriaBuilder.and(criteria, p);
        }

        //position
        if (user.getPosition() != null) {
            Predicate p = criteriaBuilder.equal(UserRoot.get("position"), user.getPosition());
            criteria = criteriaBuilder.and(criteria, p);
        }

        //countryCode
        if (user.getCountry().getId() != null) {
            Predicate p = criteriaBuilder.equal(UserRoot.get("country").get("id"), user.getCountry().getId());
            criteria = criteriaBuilder.and(criteria, p);
        }

        //docCode
        if (user.getDocument().getDocumentType().getId() != null) {
            Predicate p = criteriaBuilder.equal(UserRoot.get("document").get("documentType").get("id"), user.getDocument().getDocumentType().getId());
            criteria = criteriaBuilder.and(criteria, p);
        }
        criteriaQuery.where(criteria);
        List<User> result = em.createQuery(criteriaQuery).getResultList();
        return result;
    }

    /**
     * @see UserRepository#getUserById(int) 
     * @param id
     * @return полльзователь
     */
    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    /**
     * @see UserRepository#updateUser(User) 
     * @param user
     */
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    /**
     * @see UserRepository#saveUser(User) 
     * @param user
     */
    @Override
    public void saveUser(User user) {
        em.persist(user);
    }
}
