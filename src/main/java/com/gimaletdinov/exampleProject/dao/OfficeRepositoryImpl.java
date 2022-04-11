package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Класс реализация интерфейса OfficeRepository. Реализация методов  для доступа к БД
 */
@Repository
public class OfficeRepositoryImpl implements OfficeRepository {

    private final EntityManager em;

    @Autowired
    public OfficeRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * @see OfficeRepository#getAllOfficesByPredicat(Office) 
     * @param office
     * @return список офисов
     */
    @Override
    public List<Office> getAllOfficesByPredicat(Office office) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);

        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        criteriaQuery.select(officeRoot );

        Predicate criteria = criteriaBuilder.conjunction();

        //orgId
        Predicate pr = criteriaBuilder.equal(officeRoot.get("organization"), office.getOrganization());
        criteria = criteriaBuilder.and(criteria, pr);

        //name
        if (office.getName() != null) {
            Predicate p = criteriaBuilder.equal(officeRoot.get("name"), office.getName());
            criteria = criteriaBuilder.and(criteria, p);
        }

        //phone
        if (office.getPhone() != null) {
            Predicate p = criteriaBuilder.equal(officeRoot.get("phone"), office.getPhone());
            criteria = criteriaBuilder.and(criteria, p);
        }
        //isActive
        if (office.getIsActive() != null) {
            Predicate p = criteriaBuilder.equal(officeRoot.get("isActive"), office.getIsActive());
            criteria = criteriaBuilder.and(criteria, p);
        }
        criteriaQuery.where(criteria);
        List<Office> result = em.createQuery(criteriaQuery).getResultList();
        return result;
    }

    /**
     * @see OfficeRepository#getOfficeById(int)
     * @param id
     * @return офис
     */
    @Override
    public Office getOfficeById(int id) {
        return em.find(Office.class, id);
    }

    /**
     * @see OfficeRepository#updateOffice(Office)
     * @param office
     */
    @Override
    public void updateOffice(Office office) {
        em.merge(office);
    }

    /**
     * @see OfficeRepository#saveOffice(Office)
     * @param office
     */
    @Override
    public void saveOffice(Office office) {
        em.persist(office);
    }
}
