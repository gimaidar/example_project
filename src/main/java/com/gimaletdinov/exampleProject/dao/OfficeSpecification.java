package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания спецификации Офиса. Используется для получения списка офисов по предикату
 */
public class OfficeSpecification {

    /**
     * Метод для создания спецификации Офиса
     * @param findOffice
     * @return
     */
    public static Specification<Office> officeSpecification(Office findOffice){
        return new Specification<Office>() {
            @Override
            public Predicate toPredicate(Root<Office> officeRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //orgId
                predicates.add(criteriaBuilder.equal(officeRoot.get("organization"), findOffice.getOrganization()));

                //name
                if (findOffice.getName() != null) {
                    predicates.add(criteriaBuilder.equal(officeRoot.get("name"), findOffice.getName()));
                }

                //phone
                if (findOffice.getPhone() != null) {
                    predicates.add(criteriaBuilder.equal(officeRoot.get("phone"), findOffice.getPhone()));
                }

                //isActive
                if (findOffice.getIsActive() != null) {
                    predicates.add(criteriaBuilder.equal(officeRoot.get("isActive"), findOffice.getIsActive()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
