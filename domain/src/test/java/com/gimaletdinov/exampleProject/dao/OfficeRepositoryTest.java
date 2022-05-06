package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.TEST_OFFICE_IS_ACTIVE;
import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.TEST_OFFICE_NAME;
import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.TEST_OFFICE_PHONE;
import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.getOfficeForUpdate;
import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.getPopulateOffice;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OfficeRepositoryTest {

    private Office testOfficeInBD;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void saveTestOfficeInBD(){
        testOfficeInBD = officeRepository.save(getPopulateOffice());
        System.out.println(testOfficeInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: findAll(officeSpecification()) (Найти офисы по предикату)")
    void findAllOfficesByPredicat() {
        List<Office> officesFtomBD = officeRepository.findAll(officeSpecification());
        assertThat(officesFtomBD).isNotEmpty();
        assertThat(officesFtomBD).hasSize(1);
        assertThat(officesFtomBD.get(0)).isEqualTo(testOfficeInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: findById (Найти офис по id)")
    void findById() {
        Office officeFromBD = officeRepository.findById(testOfficeInBD.getId()).get();
        assertThat(officeFromBD).isNotNull();
        assertThat(officeFromBD).isEqualTo(testOfficeInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: updateOffice (Обновить данные офиса)")
    void updateOffice() {
        Office officeForUpdate = getOfficeForUpdate(testOfficeInBD);

        Office updatedOffice = officeRepository.save(officeForUpdate);

        assertThat(updatedOffice).isNotNull();
        assertThat(updatedOffice).isEqualTo(officeForUpdate);
    }

    @Test
    @Transactional
    @DisplayName("Тест: saveOffice (Сохранить новый офис)")
    void saveOffice() {
        Office officeForSave = getPopulateOffice();

        Office savedOffice = officeRepository.save(officeForSave);

        assertThat(savedOffice).isNotNull();
        assertThat(savedOffice).isEqualTo(officeForSave);
    }

    private Specification<Office> officeSpecification() {
        return (officeRoot, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(officeRoot.get("organization").get("id"), testOfficeInBD.getOrganization().getId()),
                criteriaBuilder.equal(officeRoot.get("name"), TEST_OFFICE_NAME),
                criteriaBuilder.equal(officeRoot.get("phone"), TEST_OFFICE_PHONE),
                criteriaBuilder.equal(officeRoot.get("isActive"), TEST_OFFICE_IS_ACTIVE));
    }
}