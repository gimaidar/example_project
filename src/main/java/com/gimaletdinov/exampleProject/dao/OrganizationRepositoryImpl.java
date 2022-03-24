package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository{

    private final EntityManager em;

    @Autowired
    public OrganizationRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Organization> getAllOrganizationsByPredicat(Organization organization) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);

        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot );

        Predicate criteria = criteriaBuilder.conjunction();

        //name
        Predicate pr = criteriaBuilder.equal(organizationRoot.get("name"), organization.getName());
        criteria = criteriaBuilder.and(criteria, pr);

        //inn
        if (organization.getInn() != null) {
            Predicate p = criteriaBuilder.equal(organizationRoot.get("inn"), organization.getInn());
            criteria = criteriaBuilder.and(criteria, p);
        }
        //isActive
        if (organization.getIsActive() != null) {
            Predicate p = criteriaBuilder.equal(organizationRoot.get("isActive"), organization.getIsActive());
            criteria = criteriaBuilder.and(criteria, p);
        }
        criteriaQuery.where(criteria);
        List<Organization> result = em.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    public Organization getOrganizationById(int id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        em.merge(organization);
    }

    @Override
    public void saveOrganization(Organization organization) {
        em.persist(organization);
    }
}
