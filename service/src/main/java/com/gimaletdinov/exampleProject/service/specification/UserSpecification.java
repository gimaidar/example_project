package com.gimaletdinov.exampleProject.service.specification;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания спецификации Пользователя. Используется для получения списка пользователей по предикату
 */
public class UserSpecification {

    /**
     * Метод для создания спецификации Пользователя
     * @param findUser
     * @return
     */
    public static Specification<User> userSpecification(UserListRequestDto findUser){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //officeId
                predicates.add(criteriaBuilder.equal(userRoot.get("office").get("id"), findUser.getOfficeId()));

                //firstName
                if (findUser.getFirstName() != null) {
                    predicates.add(criteriaBuilder.equal(userRoot.get("firstName"), findUser.getFirstName()));
                }

                //secondName
                if (findUser.getSecondName() != null) {
                    predicates.add(criteriaBuilder.equal(userRoot.get("secondName"), findUser.getSecondName()));
                }

                //middleName
                if (findUser.getMiddleName() != null) {
                    predicates.add(criteriaBuilder.equal(userRoot.get("middleName"), findUser.getMiddleName()));
                }

                //position
                if (findUser.getPosition() != null) {
                    predicates.add(criteriaBuilder.equal(userRoot.get("position"), findUser.getPosition()));
                }

                //countryCode
                if (findUser.getCountryCode() != null) {
                    predicates.add(criteriaBuilder.equal(userRoot.get("country").get("code"), findUser.getCountryCode()));
                }

                //docCode
                if (findUser.getDocCode() != null) {
                    predicates.add(criteriaBuilder.equal(userRoot.get("document").get("documentType").get("code"), findUser.getDocCode()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
