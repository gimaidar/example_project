package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.Helper.OrganizationTestHelper.getPopulateOrganizationListRequestDto;
import static com.gimaletdinov.exampleProject.dao.OrganizationSpecification.organizationSpecification;
import static com.gimaletdinov.exampleProject.Helper.OrganizationTestHelper.assertOrganizationsEquals;
import static com.gimaletdinov.exampleProject.Helper.OrganizationTestHelper.getOrganizationForUpdate;
import static com.gimaletdinov.exampleProject.Helper.OrganizationTestHelper.getPopulateOrganization;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
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
        OrganizationListRequestDto findOrganization = getPopulateOrganizationListRequestDto();
        List<Organization> organizationsFromBD = organizationRepository.findAll(organizationSpecification(findOrganization));
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
}