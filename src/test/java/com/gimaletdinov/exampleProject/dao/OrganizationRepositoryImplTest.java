package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.dao.OrganizationSpecification.organizationSpecification;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.assertOrganizationsEquals;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getOrganizationForUpdate;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganization;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
class OrganizationRepositoryImplTest {

    private Organization testOrganizationInBD;

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void saveTestOrganizationInBD(){
        testOrganizationInBD = organizationRepository.save(getPopulateOrganization());
    }

    @Test
    @Transactional
    void getAllOrganizationsByPredicat() {
        Organization findOrganization = getPopulateOrganization();
        List<Organization> organizationsFromBD = organizationRepository.findAll(organizationSpecification(findOrganization));
        assertFalse(organizationsFromBD.isEmpty());
        assertTrue(organizationsFromBD.size() == 1);
        assertOrganizationsEquals(testOrganizationInBD, organizationsFromBD.get(0));
    }

    @Test
    void getOrganizationById() {
        Organization organizationFromBD = organizationRepository.findById(testOrganizationInBD.getId()).get();
        assertNotNull(organizationFromBD);
        assertOrganizationsEquals(testOrganizationInBD, organizationFromBD);
    }

    @Test
    void updateOrganization() {
        Organization organizationForUpdate = getOrganizationForUpdate(testOrganizationInBD);

        Organization updatedOrganization = organizationRepository.save(organizationForUpdate);

        assertNotNull(updatedOrganization);
        assertOrganizationsEquals(organizationForUpdate, updatedOrganization);
    }

    @Test
    void saveOrganization() {
        Organization organizationForSave = getPopulateOrganization();
        Organization savedOrganization = organizationRepository.save(organizationForSave);

        assertNotNull(savedOrganization);
        assertOrganizationsEquals(organizationForSave, savedOrganization);
    }
}