package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.TEST_ORG_INN;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.TEST_ORG_IS_ACTIVE;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.TEST_ORG_NAME;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.assertOrganizationsEquals;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.getOrganizationForUpdate;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.getPopulateOrganization;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringJUnitConfig
//@EnableAutoConfiguration
//@SpringBootTest(classes = OrganizationRepository.class)
//@EntityScan("com/gimaletdinov/exampleProject/model")
//@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OrganizationRepositoryTest {

    private Organization testOrganizationInBD;

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void saveTestOrganizationInBD() {
        testOrganizationInBD = organizationRepository.save(getPopulateOrganization());
    }

    @Test
    @Transactional
    void getAllOrganizationsByPredicat() {
        List<Organization> organizationsFromBD = organizationRepository.findAll(organizationSpecification());
        assertFalse(organizationsFromBD.isEmpty());
        assertTrue(organizationsFromBD.size() == 1);
        assertOrganizationsEquals(testOrganizationInBD, organizationsFromBD.get(0));
    }

    @Test
    @Transactional
    void getOrganizationById() {
        Organization organizationFromBD = organizationRepository.findById(testOrganizationInBD.getId()).get();
        assertNotNull(organizationFromBD);
        assertOrganizationsEquals(testOrganizationInBD, organizationFromBD);
    }

    @Test
    @Transactional
    void updateOrganization() {
        Organization organizationForUpdate = getOrganizationForUpdate(testOrganizationInBD);

        Organization updatedOrganization = organizationRepository.save(organizationForUpdate);

        assertNotNull(updatedOrganization);
        assertOrganizationsEquals(organizationForUpdate, updatedOrganization);
    }

    @Test
    @Transactional
    void saveOrganization() {
        Organization organizationForSave = getPopulateOrganization();
        Organization savedOrganization = organizationRepository.save(organizationForSave);

        assertNotNull(savedOrganization);
        assertOrganizationsEquals(organizationForSave, savedOrganization);
    }

    private Specification<Organization> organizationSpecification() {
        return (organizationRoot, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(organizationRoot.get("name"), TEST_ORG_NAME),
                criteriaBuilder.equal(organizationRoot.get("inn"), TEST_ORG_INN),
                criteriaBuilder.equal(organizationRoot.get("isActive"), TEST_ORG_IS_ACTIVE));
    }
}
