package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.TEST_ORG_ID;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.assertOrganizationsEquals;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getOrganizationForUpdate;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganization;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OrganizationRepositoryImplTest {

    private Organization newTestOrganization = getPopulateOrganization();

    @Autowired
    private OrganizationRepository organizationRepository;


    @Test
    @Transactional
    void getAllOrganizationsByPredicat() {
        List<Organization> organizationsFromBD = organizationRepository.getAllOrganizationsByPredicat(newTestOrganization);
        assertFalse(organizationsFromBD.isEmpty());
        assertTrue(organizationsFromBD.size() == 1);
        assertOrganizationsEquals(newTestOrganization, organizationsFromBD.get(0));
    }

    @Test
    @Transactional
    void getOrganizationById() {
        Organization organizationFromBD = organizationRepository.getOrganizationById(TEST_ORG_ID);
        assertNotNull(organizationFromBD);
        assertOrganizationsEquals(newTestOrganization, organizationFromBD);
    }

    @Test
    @Transactional
    void updateOrganization() {
        Organization organizationFromBD = organizationRepository.getOrganizationById(TEST_ORG_ID);
        Organization organizationForUpdate = getOrganizationForUpdate(organizationFromBD);

        organizationRepository.updateOrganization(organizationForUpdate);
        Organization updatedOrganization = organizationRepository.getOrganizationById(TEST_ORG_ID);

        assertNotNull(updatedOrganization);
        assertOrganizationsEquals(organizationForUpdate, updatedOrganization);
    }

    @Test
    @Transactional
    void saveOrganization() {
        organizationRepository.saveOrganization(newTestOrganization);
        Organization savedOrganization = organizationRepository.getOrganizationById(TEST_ORG_ID + 1);

        assertNotNull(savedOrganization);
        assertOrganizationsEquals(newTestOrganization, savedOrganization);
    }
}