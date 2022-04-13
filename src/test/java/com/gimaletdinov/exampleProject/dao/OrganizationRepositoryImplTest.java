package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OrganizationRepositoryImplTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    @Transactional
    void getAllOrganizationsByPredicat() {
        Organization organization = new Organization();
        organization.setName("test_org name");

        List<Organization> resultList = organizationRepository.getAllOrganizationsByPredicat(organization);
        assertTrue(resultList.size() > 0);
    }

    @Test
    @Transactional
    void getOrganizationById() {
        Organization organization = organizationRepository.getOrganizationById(1);
        assertEquals(organization.getId(), 1);
        assertNotNull(organization);
    }

    @Test
    @Transactional
    void updateOrganization() {
        Organization organization = organizationRepository.getOrganizationById(1);
        organization.setName("update name");
        organization.setFullName("update fullName");
        organization.setInn("9999999999");
        organization.setKpp("9999999999");
        organization.setAddress("update address");
        organization.setPhone("99999999999");
        organization.setIsActive(false);

        organizationRepository.updateOrganization(organization);
        Organization updatedOrganization = organizationRepository.getOrganizationById(1);

        assertNotNull(updatedOrganization);
        assertEquals(organization.getId(), updatedOrganization.getId());
        assertEquals(organization.getName(), updatedOrganization.getName());
        assertEquals(organization.getFullName(), updatedOrganization.getFullName());
        assertEquals(organization.getInn(), updatedOrganization.getInn());
        assertEquals(organization.getKpp(), updatedOrganization.getKpp());
        assertEquals(organization.getAddress(), updatedOrganization.getAddress());
        assertEquals(organization.getPhone(), updatedOrganization.getPhone());
        assertEquals(organization.getIsActive(), updatedOrganization.getIsActive());
    }

    @Test
    @Transactional
    void saveOrganization() {
        Organization organization = new Organization();
        organization.setName("save name");
        organization.setFullName("save fullName");
        organization.setInn("9999999999");
        organization.setKpp("9999999999");
        organization.setAddress("save address");
        organization.setPhone("99999999999");
        organization.setIsActive(false);

        organizationRepository.saveOrganization(organization);
        Organization savedOrganization = organizationRepository.getOrganizationById(3);

        assertNotNull(savedOrganization);
        assertEquals(organization.getName(), savedOrganization.getName());
        assertEquals(organization.getFullName(), savedOrganization.getFullName());
        assertEquals(organization.getInn(), savedOrganization.getInn());
        assertEquals(organization.getKpp(), savedOrganization.getKpp());
        assertEquals(organization.getAddress(), savedOrganization.getAddress());
        assertEquals(organization.getPhone(), savedOrganization.getPhone());
        assertEquals(organization.getIsActive(), savedOrganization.getIsActive());
    }
}