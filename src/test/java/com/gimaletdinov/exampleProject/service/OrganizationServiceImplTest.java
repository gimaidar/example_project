package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OrganizationServiceImplTest {

    @Autowired
    private OrganizationService organizationService;


    @Test
    @Transactional
    void getAllOrganizationsByPredicat() {
        OrganizationListRequestDto requestDto = new OrganizationListRequestDto();
        requestDto.setName("test_org name");

        List<OrganizationListResponseDto> resultList = organizationService.getAllOrganizationsByPredicat(requestDto);
        assertTrue(resultList.size() > 0);

        requestDto.setName("not exist");
        assertThrows(NoSuchObjectException.class, () -> organizationService.getAllOrganizationsByPredicat(requestDto));
    }

    @Test
    void getOrganizationById() {
        this.getOrganizationByIdFromRepository();
    }

    @Test
    @Transactional
    void updateOrganization() {
        OrganizationUpdateRequestDto requestDto = new OrganizationUpdateRequestDto();
        requestDto.setId(1);
        requestDto.setName("update name");
        requestDto.setFullName("update fullName");
        requestDto.setInn("9999999999");
        requestDto.setKpp("9999999999");
        requestDto.setAddress("update address");
        requestDto.setPhone("99999999999");
        requestDto.setIsActive(false);

        organizationService.updateOrganization(requestDto);
        Organization updatedOrganization = organizationService.getOrganizationByIdFromRepository(1);

        assertNotNull(updatedOrganization);
        assertEquals(requestDto.getId(), updatedOrganization.getId());
        assertEquals(requestDto.getName(), updatedOrganization.getName());
        assertEquals(requestDto.getFullName(), updatedOrganization.getFullName());
        assertEquals(requestDto.getInn(), updatedOrganization.getInn());
        assertEquals(requestDto.getKpp(), updatedOrganization.getKpp());
        assertEquals(requestDto.getAddress(), updatedOrganization.getAddress());
        assertEquals(requestDto.getPhone(), updatedOrganization.getPhone());
        assertEquals(requestDto.getIsActive(), updatedOrganization.getIsActive());

    }

    @Test
    @Transactional
    void saveOrganization() {
        OrganizationSaveRequestDto requestDto = new OrganizationSaveRequestDto();
        requestDto.setName("save name");
        requestDto.setFullName("save fullName");
        requestDto.setInn("9999999999");
        requestDto.setKpp("9999999999");
        requestDto.setAddress("save address");
        requestDto.setPhone("99999999999");
        requestDto.setIsActive(false);

        assertThrows(NoSuchObjectException.class, () -> organizationService.getOrganizationByIdFromRepository(3));

        organizationService.saveOrganization(requestDto);
        Organization savedOrganization = organizationService.getOrganizationByIdFromRepository(3);

        assertNotNull(savedOrganization);
        assertEquals(requestDto.getName(), savedOrganization.getName());
        assertEquals(requestDto.getFullName(), savedOrganization.getFullName());
        assertEquals(requestDto.getInn(), savedOrganization.getInn());
        assertEquals(requestDto.getKpp(), savedOrganization.getKpp());
        assertEquals(requestDto.getAddress(), savedOrganization.getAddress());
        assertEquals(requestDto.getPhone(), savedOrganization.getPhone());
        assertEquals(requestDto.getIsActive(), savedOrganization.getIsActive());

    }

    @Test
    @Transactional
    void getOrganizationByIdFromRepository() {
        Organization organization = organizationService.getOrganizationByIdFromRepository(1);
        assertEquals(organization.getId(), 1);
        assertNotNull(organization);
    }
}