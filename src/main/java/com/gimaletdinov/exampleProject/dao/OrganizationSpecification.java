package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания спецификации Организации. Используется для получения списка организаций по предикату
 */
public class OrganizationSpecification {

    /**
     * Метод для создания спецификации Организации
     * @param findOrganization
     * @return
     */
    public static Specification<Organization> organizationSpecification(Organization findOrganization){
        return new Specification<Organization>() {
            @Override
            public Predicate toPredicate(Root<Organization> organizationRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //name
                predicates.add(criteriaBuilder.equal(organizationRoot.get("name"), findOrganization.getName()));

                //inn
                if (findOrganization.getInn() != null) {
                    predicates.add(criteriaBuilder.equal(organizationRoot.get("inn"), findOrganization.getInn()));
                }

                //isActive
                if (findOrganization.getIsActive() != null) {
                    predicates.add(criteriaBuilder.equal(organizationRoot.get("isActive"), findOrganization.getIsActive()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
