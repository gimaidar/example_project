package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;
import org.junit.jupiter.api.BeforeEach;
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
import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.assertOfficesEquals;
import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.getOfficeForUpdate;
import static com.gimaletdinov.exampleProject.helper.OfficeTestHelper.getPopulateOffice;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void getAllOfficesByPredicat() {
        List<Office> officesFtomBD = officeRepository.findAll(officeSpecification());
        assertFalse(officesFtomBD.isEmpty());
        assertTrue(officesFtomBD.size() == 1);
        assertOfficesEquals(testOfficeInBD, officesFtomBD.get(0));
    }

    @Test
    @Transactional
    void getOfficeById() {
        Office officeFromBD = officeRepository.findById(testOfficeInBD.getId()).get();
        assertNotNull(officeFromBD);
        assertOfficesEquals(testOfficeInBD, officeFromBD);
    }

    @Test
    @Transactional
    void updateOffice() {
        Office officeForUpdate = getOfficeForUpdate(testOfficeInBD);

        Office updatedOffice = officeRepository.save(officeForUpdate);

        assertNotNull(updatedOffice);
        assertOfficesEquals(officeForUpdate, updatedOffice);
    }

    @Test
    @Transactional
    void saveOffice() {
        Office officeForSave = getPopulateOffice();

        Office savedOffice = officeRepository.save(officeForSave);

        assertNotNull(savedOffice);
        assertOfficesEquals(officeForSave, savedOffice);
    }

    private Specification<Office> officeSpecification() {
        return (officeRoot, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(officeRoot.get("organization").get("id"), testOfficeInBD.getOrganization().getId()),
                criteriaBuilder.equal(officeRoot.get("name"), TEST_OFFICE_NAME),
                criteriaBuilder.equal(officeRoot.get("phone"), TEST_OFFICE_PHONE),
                criteriaBuilder.equal(officeRoot.get("isActive"), TEST_OFFICE_IS_ACTIVE));
    }
}